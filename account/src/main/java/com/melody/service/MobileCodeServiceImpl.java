package com.melody.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.melody.common.constant.BusinessCodes;
import com.melody.common.constant.RedisCodes;

import com.melody.common.sms.SMSManager;
import com.melody.common.utils.AuthCodeUtils;
import com.melody.dao.UserDAO;
import com.melody.exception.BusinessException;
import com.melody.mobile.api.MobileCodeService;
import com.melody.mobile.api.PrivateMobileCodeService;
import com.melody.mobile.dto.MobileCodeEnter;
import com.melody.mobile.dto.MobileCodeResult;
import com.melody.redis.RedisCache;
import com.melody.redis.RedisLocker;
import com.melody.system.api.SysConfigService;
import com.melody.user.dto.User;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.ObjectUtils;

/**
 * ClassName: MobileSmsSender
 *
 * @author Ciro
 * @Description: Mobile verification code send
 * @date 2015-11-25
 */
@Service(group = "mobileCodeService")
public class MobileCodeServiceImpl implements MobileCodeService {
    private static final Logger log = LoggerFactory.getLogger(MobileCodeServiceImpl.class);
    //请及时输入，五分钟后失效。
    public final static String MESSAGE_DATA = "【神马贷款】您的验证码是%s";
    //  public final static String MESSAGE_DATA = "您的验证码为：%s, 请及时输入，五分钟后失效。";

    @Autowired
    RedisCache redisCache;

    @Autowired
    private UserDAO userDAO;


    @Autowired
    SysConfigService sysConfigService;
    @Autowired
    RedisLocker redisLocker;

    @Reference(group = "privateMobileCodeService")
    PrivateMobileCodeService privateMobileCodeService;


    public MobileCodeResult getMobileCode(MobileCodeEnter mce) {

        log.info("getMobileCode" + mce.toString());
        MobileCodeResult mcr = new MobileCodeResult();
        String sendType = mce.getSendType();
        String mobileCode = AuthCodeUtils.createAuthCode();

        if (StringUtils.equals("1", sendType)) {// 1.注册手机验证码发送 [校验是否注册]
            this.sendRegisterMobileCode(mce, mcr);
        } else if (StringUtils.equals("2", sendType)) {// 2.忘记密码 [校验是否实名制]
            log.info("getMobileCode" + mce.toString());
            log.info("getMobileCode  sendType: " + sendType);
            this.sendRetrievePwdMobileCode(mce, mcr);
        } else {// 3.其他
            throw new BusinessException("-1", "短信发送类型不正确，请重新选择！");
        }
        MobileCodeResult result = privateMobileCodeService.sendMobileCode(mce.getMobileNo(), String.format(MESSAGE_DATA, mobileCode),
                SMSManager.YP);

        if (result == null || !result.getCode().equals(BusinessCodes.SUCCESS)) {
            //throw new BusinessException(BusinessCodes.ACCOUNT_MOBILECODE_SEDNTYPE, "send moblie code is error by sendType=" + sendType);
            throw new BusinessException(result.getCode(), result.getMessage());
        }

        //把验证码保存到redis中
        redisCache.setString(RedisCodes.MOBILE_SMS + mce.getMobileNo(), mobileCode, RedisCodes.SMS_CODE_EXPIRE_TIME);

        mcr.setCode(BusinessCodes.SUCCESS);
        mcr.setMessage("Success");
        return mcr;
    }

    private void sendRetrievePwdMobileCode(MobileCodeEnter mce, MobileCodeResult mcr) {
        User user = userDAO.getUserByMobileNo(mce.getMobileNo());
        if (!ObjectUtils.isEmpty(user)) {
            mcr.setCertification(StringUtils.isEmpty(user.getCertification()) ? "0" : user.getCertification());
        } else {
            mcr.setCertification("0");
        }
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(BusinessCodes.ACCOUNT_NOREGOVERLOAD, "The user no registered");//该用户未注册
        }
    }

    private void sendRegisterMobileCode(MobileCodeEnter mobileCodeEnter, MobileCodeResult mobileCodeResult) {
        //校验验证码
//        String clientType = mobileCodeEnter.getCertification();
        String clientType = mobileCodeEnter.getClientType();
        String pictureCode = mobileCodeEnter.getMobileCode();
        String mobileNo = mobileCodeEnter.getMobileNo();


        if ("A|I|M".lastIndexOf(clientType) < 0) {
            handleVerifyCode(mobileNo, pictureCode);
        }

        User user = userDAO.getUserByMobileNo(mobileCodeEnter.getMobileNo());
        if (!ObjectUtils.isEmpty(user)) {
            throw new BusinessException(BusinessCodes.ACCOUNT_REGOVERLOAD, "The user has been registered");//该用户已经注册
        }
    }

    private void handleVerifyCode(String mobileNo, String pictureCode) {
        if (StringUtils.isBlank(pictureCode)) {
            throw new BusinessException("-1", "请重新输入验证码！");//该用户已经注册
        }
        String lockerKey = RedisCodes.VERIFYCODE + mobileNo;
        try {
            redisLocker.lock("Lock_" + lockerKey);
            String _pictureCode = redisCache.getString(lockerKey);
            if (StringUtils.isBlank(pictureCode)) {//手机验证码失效
                throw new BusinessException(BusinessCodes.ACCOUNT_MOBILECODE_INVALID);
            }
            if (!StringUtils.equals(pictureCode, _pictureCode)) {
                throw new BusinessException(BusinessCodes.ACCOUNT_VERIFYCODE);
            }
        } finally {
            //删除验证码
            redisCache.delKey(lockerKey);
            redisLocker.unlock("Lock_" + lockerKey);
        }
    }


    @Override
    public MobileCodeResult verifyMobileCode(MobileCodeEnter mobileCodeEnter) {
        MobileCodeResult result = new MobileCodeResult();
        result.setCode(BusinessCodes.SUCCESS);
        User redisUser = userDAO.getUserByMobileNo(mobileCodeEnter.getMobileNo());

        String rmc = redisCache.getString(RedisCodes.MOBILE_SMS + mobileCodeEnter.getMobileNo());

        String checkCode = sysConfigService.getSysParameter("CHECKCODE"/*登录，注册，忘记密码的万能密码*/);
        if (mobileCodeEnter.getMobileCode().equals(checkCode)) {
            return result;
        }
        if (redisUser == null) {
            throw new BusinessException(BusinessCodes.ACCOUNT_FINDUSER_ERROR, "ACCOUNT_FINDUSER_ERROR");//没有找到该用户!
        }
        if (StringUtils.isEmpty(rmc)) {
            throw new BusinessException(BusinessCodes.ACCOUNT_MOBILECODE, "ACCOUNT_MOBILECODE");//验证码已经过期
        }
        if (!rmc.equals(mobileCodeEnter.getMobileCode())) {
            throw new BusinessException(BusinessCodes.ACCOUNT_MOBILECODE, "the mobile code is error");//验证码错误
        }
        //不检验身份证
//        if (redisUser.getCertification().equals("1") && !redisUser.getCardId().equals(mobileCodeEnter.getIdCard())) {
//            throw new BusinessException(BusinessCodes.ACCOUNT_IDCARD_ERROR, "idCard is error");//身份证号码不对
//        }
        //redisCache.delKey(RedisCodes.MOBILE_SMS + mobileCodeEnter.getMobileNo());
        return result;
    }


    public static void main(String[] args) {
        try {
            SMSManager.doSendSms("18516560317", "领头鸟投资验证码【8888】", SMSManager.DHST);
            System.out.print("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
