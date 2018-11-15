package com.melody.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.melody.base.GeneralResult;
import com.melody.common.constant.BusinessCodes;
import com.melody.common.constant.RedisCodes;
import com.melody.common.sms.SMSManager;
import com.melody.common.utils.DateUtils;
import com.melody.exception.BusinessException;
import com.melody.mobile.api.PrivateMobileCodeService;
import com.melody.mobile.dto.MobileCodeResult;
import com.melody.redis.RedisCache;
import com.melody.system.api.SysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: MobileSmsSender
 *
 * @author Ciro
 * @Description: Mobile verification code send
 * @date 2015-11-25
 */
@Service(group = "privateMobileCodeService")
public class PrivateMobileCodeServiceImpl implements PrivateMobileCodeService {
    private static final Log log = LogFactory.getLog(PrivateMobileCodeServiceImpl.class);

    public static final String DAY_COUNT = "DAY_COUNT";//发送短信失败
    public static final String MOBILE_NO = "MOBILE_NO";//发送短信失败

    // public static final String[] mobileNos = new String[]{SMSManager.DHST,SMSManager.XWKJ, SMSManager.CL};
    public static final String[] mobileNos = new String[]{SMSManager.YP};

    private static AtomicInteger counter = new AtomicInteger(1);

    @Autowired
    RedisCache redisCache;

    @Reference(group = "sysConfigService")
    SysConfigService sysConfigService;

    @Override
    public GeneralResult sendMobileCode(String mobileNo, String messageData, String type) {
        //校验每个手机每天发送的次数
        //TODO: enable it later
        //    verifySendMobileCode(mobileNo);
        //如果 快超过Integer的最大值的时候，还原counter
        if (counter.get() > Integer.MAX_VALUE - 10000) {
            counter.set(1);
        }
        //按照顺序选择读库
        //   int index = counter.incrementAndGet() % 3;
        try {
            SMSManager.doSendSms(mobileNo, messageData, mobileNos[0]);
        } catch (Exception e) {
            log.error("sendMobileCode is error !!!! mobileNo:" + mobileNo, e);
            throw new BusinessException(BusinessCodes.ACCOUNT_SMS_ERROR, e);//已超过当天发送短信总条数
        }
        return GeneralResult.isOk();
    }

    @Override
    public GeneralResult sendMobileCode(String[] strings, String messageData, String type) {
        for (String mobileNo : strings) {
            try {
                sendMobileCode(mobileNo, messageData, type);
            } catch (Exception e) {
                log.error("sendMobileCode is error !!!! mobileNo:" + mobileNo, e);
            }
        }
        return GeneralResult.isOk();
    }

    private void verifySendMobileCode(String mobileNo) {
        long unixTimestamp = DateUtils.getUnixTimestampOf24() - 60;
        Map<String, String> moblie = redisCache.hashGetAll(RedisCodes.MOBILE_SMS_VERIFY + mobileNo);
        if (moblie.size() == 0) {
            Map map = new HashMap();
            map.put(MOBILE_NO, mobileNo);
            map.put(DAY_COUNT, "0");//每天的次数
            redisCache.hashMultipleSet(RedisCodes.MOBILE_SMS_VERIFY + mobileNo, map);
            redisCache.expireAt(RedisCodes.MOBILE_SMS_VERIFY + mobileNo, unixTimestamp);
            return;
        }
        String dayCount = redisCache.hashGet(RedisCodes.MOBILE_SMS_VERIFY + mobileNo, DAY_COUNT);

        dayCount = StringUtils.defaultIfBlank(dayCount, "0");
        int iDayCount = Integer.parseInt(dayCount) + 1;

        String sysDayCount = sysConfigService.getSysParameter("SMS_DAY_COUNT"/**每人每天发送短信的次数限制**/);
        if (iDayCount > Integer.parseInt(sysDayCount)) {
            throw new BusinessException(BusinessCodes.GATEWAY_SMS_MAX);//已超过当天发送短信总条数
        }
        redisCache.hashSet(RedisCodes.MOBILE_SMS_VERIFY + mobileNo, DAY_COUNT, iDayCount + "");
        redisCache.expireAt(RedisCodes.MOBILE_SMS_VERIFY + mobileNo, unixTimestamp);
    }


    public static void main(String[] args) {

        for (int i = -1; i < 100; i++) {
            try {
                SMSManager.doSendSms("18516560317", "领头鸟投资验证码【8888】", SMSManager.DHST);
                System.out.print("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
