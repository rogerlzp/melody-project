package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.melody.common.constant.BusinessCodes;
import com.melody.common.constant.RedisCodes;
import com.melody.common.constant.UserLevelEnum;

import com.melody.common.utils.AuthCodeUtils;
import com.melody.common.utils.TokenUtils;
import com.melody.dao.AccountDao;
import com.melody.dao.UserDAO;
import com.melody.exception.BusinessException;
import com.melody.redis.RedisCache;
import com.melody.system.api.SysConfigService;
import com.melody.user.api.RegisterService;
import com.melody.user.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Service(group = "registerService", timeout = 10000)
public class RegisterServiceImpl implements RegisterService {

    private static final Log log = LogFactory.getLog(RegisterServiceImpl.class);
    @Autowired
    RedisCache redisCache;

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AccountDao accountDao;


    @Autowired
    BaseServiceImpl baseService;

//    @Autowired
//    BaseServiceImpl baseService;
//    @Autowired
//    private PushRelationService pushRelationService;

    @Override
    public RegisterResult getPictureCode(RegisterEnter registerPara) {
        RegisterResult registerResult = new RegisterResult();


        String pictureCode = AuthCodeUtils.createAuthCode();
        redisCache.setString(RedisCodes.VERIFYCODE + registerPara.getMachineNo(), pictureCode, RedisCodes.VERIFYCODE_TIME);

        registerResult.setPictureCode(pictureCode);
        registerResult.setCode(BusinessCodes.SUCCESS);

        if (log.isInfoEnabled()) {
            log.info("getPictureCode>>>>>>RegisterResult:" + registerResult);
        }
        return registerResult;
    }

    private boolean checkMobileCode(String mobileNo, String mobileCode) {
        String rmc = redisCache.getString(RedisCodes.MOBILE_SMS + mobileNo);
        String checkCode = sysConfigService.getSysParameter("CHECKCODE"/*
         * 登录，注册，
         * 忘记密码的万能密码
         */);

        if (mobileCode.equals(checkCode)) {// 万能密码
            redisCache.delKey(RedisCodes.MOBILE_SMS + mobileNo);
            return true;
        }
        if (StringUtils.isBlank(rmc)) {
            throw new BusinessException(BusinessCodes.ACCOUNT_MOBILECODE);
        }

        if (rmc.equals(mobileCode)) {
            redisCache.delKey(RedisCodes.MOBILE_SMS + mobileNo);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    @Override
    public RegisterResult registerUser(RegisterEnter registerPara) {
        RegisterResult registerResult = new RegisterResult();
        this.validatorUser(registerPara);

        // 1.判断当前用户是否存在。
        int count = userDAO.countUser(registerPara.getMobileNo());
        // 2.不存在则添加
        if (count > 0) {
            throw new BusinessException(BusinessCodes.ACCOUNT_REGOVERLOAD, "The user already exists,mobileCode:"
                    + registerPara.getMobileCode());
        }

        long sequenceId = baseService.getNextSequence("TS_USER");
        String sessionKey = redisCache.setSeeionKey(String.valueOf(sequenceId), registerPara.getMobileNo());
        // 初始化user对象
        User user = initUser(registerPara);
        user.setUserId(sequenceId);
        // 插入用户
        int countUser = userDAO.insertUser(user);// 保存对象

        // 插入用户扩展信息 2016-04-01 wdj
//        if (registerPara.getChannel() != null && !(registerPara.getChannel().equalsIgnoreCase(""))) {
//            UserExt userExt = new UserExt();
//            userExt.setId(baseService.getNextSequence("TS_USER_EXP"));
//            userExt.setUserId(sequenceId);
//            userExt.setChannel(registerPara.getChannel());
//            userDAO.insertUserExt(userExt);
//        }

        if (countUser > 0) {
            // 插入账户
            Account account = initAccount(user);
//            int tem = accountDao.insertAccount(account);
//
//            // 插入鸟币账户
//            BirdCoinAccount birdCoinAccount = initBirdCoinAccount(user, account);
//            accountDao.insertBirdCoinAccount(birdCoinAccount);
//            // 插入用户等级
            UserLevel userLevel = initUserLevel(user.getUserId());
            accountDao.insertUserLevel(userLevel);
            // TODO: 2016/1/4 合伙人需要处理下。 如果推荐人不存在需要给用户提示一下
//            if (StringUtils.isNotBlank(registerPara.getPartnerMobile())) {// 用户填写推荐人
//                RepartnerEnter repartnerEnter = new RepartnerEnter();
//                repartnerEnter.setMobileNo(registerPara.getPartnerMobile());
//                repartnerEnter.setUserId(user.getId());
//                repartnerEnter.setUserMobileNo(user.getMobile());
//                String message = plenish(repartnerEnter);
//                registerResult.setMessage(message);
//            }
            // 理财金券
//            CouponEnter couponEnter_lcjq = initLcjqCouponEnter(user.getId());
//            couponService.issueCoupon(couponEnter_lcjq);
//
//            // 给返现券
//            // UserCoupon userCoupon=initUserCoupon(user);
//            CouponEnter couponEnter = initRegisterCouponEnter(user.getId());
//            couponService.issueCoupon(couponEnter);

            // 鸟蛋
            // goldenEggService.incr(user.getId(), 1, 0,
            // DateUtils.formatDate2(new Date()));
//            goldenEggService.addGoldenEgg(user.getId(), GoldenEggEnum.BIZ_TYPE_ZC.getEnName(), "",
//                    GoldenEggEnum.WINNING_TYPE_FBZ.getEnName());


            // 插入缓存
            redisCache.updateUser(user);
            registerResult.setSessionKey(sessionKey);
            registerResult.setCode(BusinessCodes.SUCCESS);
        }
        //TODO: add for push message later
//        try {
//            pushRelationService.saveUserDevice(registerPara.getRegistrationId(), sequenceId);
//        } catch (Exception e) {
//            log.error("save user device relation falure, registrationId:" + registerPara.getRegistrationId() + " userId:" + sequenceId, e);
//        }
        return registerResult;
    }

    private void validatorUser(RegisterEnter registerPara) {
        if (!checkMobileCode(registerPara.getMobileNo(), registerPara.getMobileCode())) {
            throw new BusinessException(BusinessCodes.ACCOUNT_MOBILECODE, "the mobile code is error");// 手机号码错误
        }

        if (!StringUtils.equals(registerPara.getReadAndAgree(), "1")) {
            throw new BusinessException(BusinessCodes.ACCOUNT_READANDAGREE, "readAndAgree is not 1");// 没选择已阅读按钮
        }
    }

    private UserLevel initUserLevel(long id) {
        UserLevel userLevel = new UserLevel();
        Long sequenceId = baseService.getNextSequence("TT_USER_LEVEL");
        userLevel.setId(sequenceId);
        userLevel.setUserId((int) id);
        userLevel.setLevelId(UserLevelEnum.NO_LEVEL.getEnName());
        return userLevel;
    }

    private Account initAccount(User user) {
        Account account = new Account();
        Long sequenceId = baseService.getNextSequence("TT_ACCOUNT");
        account.setId(sequenceId);
        account.setUserId(user.getUserId());
        account.setCreateDate(new Date());
        account.setUpdateDate(new Date());
        account.setTotalAsset(0.00);
        account.setUsableBalance(0.00);
        account.setFrozenAmount(0.00);
        return account;
    }


    private User initUser(RegisterEnter registerPara) {
        User user = new User();
        user.setMobileNo(registerPara.getMobileNo());
        user.setPassword(TokenUtils.getInstance().MD5(registerPara.getPassword()));
        user.setStatus("0");// state=0 正常，其他不正常
//        user.setCertification("0");// 0未实名制，1已实名制

        /** 修改表后添加的字段 */
        user.setUpdateDate(new Date());// 修改时间
        user.setReferralCode("");// 推荐码：6位
        user.setGuestType("0");// 客户类型:个人是0，公司是1
        user.setIsExperience(0);
        user.setIsStaff(0);
        user.setIsFirstOrder(0);
        return user;
    }

}
