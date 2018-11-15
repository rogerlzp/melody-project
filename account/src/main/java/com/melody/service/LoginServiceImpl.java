package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.melody.annotation.Role;
import com.melody.base.GeneralResult;
import com.melody.common.constant.BusinessCodes;
import com.melody.common.constant.RedisCodes;
import com.melody.common.constant.UserLoginEnum;
import com.melody.common.utils.TokenUtils;
import com.melody.dao.UserDAO;
import com.melody.exception.BusinessException;
import com.melody.redis.RedisCache;
import com.melody.system.api.SysConfigService;
import com.melody.user.api.LoginService;

import com.melody.user.api.PushRelationService;
import com.melody.user.dto.User;
import com.melody.user.dto.UserLogin;
import com.melody.user.dto.UserQueryEnter;
import com.melody.user.dto.UserQueryResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by liuyw on 2015/11/11.
 */

@Service(group = "loginService", timeout = 20000)
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserDAO userDAO;
    @Autowired
    RedisCache redisCache;
    @Autowired
    BaseServiceImpl baseService;
    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    private PushRelationService pushRelationService;

    private void handleVerifyCode(UserQueryEnter userQueryPara) {
        String pictureCode = redisCache.getString(RedisCodes.VERIFYCODE + userQueryPara.getMachineNo());
        log.debug("handleVerifyCode===>The current pictureCode=" + pictureCode);
        String checkCode = sysConfigService.getSysParameter("CHECKCODE"/*
         * 登录，注册，
         * 忘记密码的万能密码
         */);
        if (!userQueryPara.getPictureCode().equals(checkCode)) {
            if (StringUtils.isBlank(pictureCode)) {// 手机验证码失效
                throw new BusinessException(BusinessCodes.ACCOUNT_MOBILECODE_INVALID, "account_mobilecode_invalid");
            }
            if (!StringUtils.equals(userQueryPara.getPictureCode(), pictureCode)) {
                throw new BusinessException(BusinessCodes.ACCOUNT_VERIFYCODE, "verifyCode error");
            }
        }
        // 删除验证码
        redisCache.delKey(RedisCodes.VERIFYCODE + userQueryPara.getMachineNo());
    }

    public GeneralResult doLogin(UserQueryEnter userQueryPara) {
        UserQueryResult result = new UserQueryResult();
//        result.setCode(BusinessCodes.SUCCESS);

        String password = TokenUtils.getInstance().MD5(userQueryPara.getPassword());
        // 0.判断验证码, 不需要验证码
//		handleVerifyCode(userQueryPara);
        // 1.调用接口登录
        User redisUser = redisCache.getObject(RedisCodes.USER + userQueryPara.getMobileNo(), User.class);
        if (null == redisUser) {
            redisUser = userDAO.getUserByMobileNo(userQueryPara.getMobileNo());
            if (redisUser == null) {
                throw new BusinessException(BusinessCodes.ACCOUNT_NOREGOVERLOAD, "User not registered!");
            }
            redisCache.updateUser(redisUser);// 永不过期
        }

        if (!password.equals(redisUser.getPassword())) {
            throw new BusinessException(BusinessCodes.ACCOUNT_PASSWORD, "password error!");
        }
        String sessionKey = redisCache.setSeeionKey(String.valueOf(redisUser.getUserId()), userQueryPara.getMobileNo());
        insertUserLoginLog(redisUser, sessionKey, "login", userQueryPara.getIp());// 处理登录日志

        result.setSessionKey(sessionKey);

        try {
            pushRelationService.saveUserDevice(userQueryPara.getRegistrationId(), redisUser.getUserId());
        } catch (Exception e) {
            log.error(
                    "save user device relation falure, registrationId:" + userQueryPara.getRegistrationId() + " userId:"
                            + redisUser.getUserId(), e);
        }
       return GeneralResult.isOk().data(result);
    }

    @Role
    public GeneralResult doLogout(UserQueryEnter userQueryEnter) {
        UserQueryResult userQueryResult = new UserQueryResult();
        redisCache.delKey(RedisCodes.SESSION + userQueryEnter.getSessionKey());
//        userQueryResult.setCode(BusinessCodes.SUCCESS);
        pushRelationService.saveUserDevice(userQueryEnter.getRegistrationId(), 0);
        return GeneralResult.isOk().data(userQueryResult);
    }

    /**
     * 插入登录日志
     *
     * @param user
     * @param sessionKey
     */
    private void insertUserLoginLog(User user, String sessionKey, String logType, String ip) {

        // 查询出最后一次登录的日志。
        UserLogin lastUserLoginLog = userDAO.findLastUserLoginLog(String.valueOf(user.getUserId()));
        String lastSessionKey = "";
        if (lastUserLoginLog != null) {// 如果存在这样的数据则先干掉
            lastSessionKey = lastUserLoginLog.getSessionKey();
        }

        // 插入新的登录日志
        UserLogin userLogin = new UserLogin();
        int sequenceId = baseService.getNextSequence("TL_USER_LOGIN").intValue();
        userLogin.setId(sequenceId);
        userLogin.setUserId(user.getUserId());
        userLogin.setUserName(user.getUserName());
        userLogin.setMobileNo(user.getMobileNo());
        userLogin.setLoginStatus(UserLoginEnum.UserLogin_INIT.getEnName());
        userLogin.setSessionKey(sessionKey);
        userLogin.setLoginType(logType);
        userLogin.setLoginIp(ip);
        int i = userDAO.insertUserLoginLog(userLogin);

        if (i > 0 && lastUserLoginLog != null) {// 插入新的数据后需要把原来的数据设置为已过期
            userDAO.updatLastUserLoginLogById(lastUserLoginLog.getId(), UserLoginEnum.UserLogin_END.getEnName());
        }
        redisCache.delKey(RedisCodes.SESSION + lastSessionKey);
    }
}
