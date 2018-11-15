package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.melody.base.GeneralResult;
import com.melody.common.constant.BusinessCodes;
import com.melody.common.constant.RedisCodes;
import com.melody.common.constant.UserLevelEnum;
import com.melody.common.utils.TokenUtils;
import com.melody.dao.AccountDao;
import com.melody.dao.UserDAO;
import com.melody.dao.UserWxMapper;
import com.melody.exception.BusinessException;
import com.melody.redis.RedisCache;
import com.melody.user.api.WxService;
import com.melody.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Service(group = "wxService", timeout = 10000)
public class WxServiceImpl implements WxService {

    @Autowired
    UserWxMapper userWxMapper;

    @Autowired
    BaseServiceImpl baseService;

    @Autowired
    RedisCache redisCache;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AccountDao accountDao;

    @Override
    public UserQueryResult create3rdSession(String wxOpenId, String wxSessionKey, Long expires) {
        // 检查是否存在openId, 如果有的话，则关联起来；没有的话，也不做其他作用
        // 将openId, sessionKey存入到Redis中，返回sessionKey, 等待后续使用

        UserQueryResult userQueryResult = new UserQueryResult();
        // 生成sessionKey
        UserWx redisUser = redisCache.getObject(RedisCodes.WX_USER + wxOpenId, UserWx.class);
        if (null == redisUser) {
            redisUser = userWxMapper.selectByOpenId(wxOpenId);
            if (redisUser == null) { // 没有，第一次登录，后续插入资料
//                UserWx userWx2 = new UserWx();
                long userId = initUser(wxOpenId);
                redisUser = new UserWx();
                redisUser.setUserId(userId);
                redisUser.setOpenId(wxOpenId);
                long userWxId = baseService.getNextSequence("TT_USER_WX");
                redisUser.setId(userWxId);
                userWxMapper.insert(redisUser);
            }
            redisCache.updateWXUser(redisUser);// 不过期
        }

        String sessionKey = redisCache.setSeeionKey(String.valueOf(redisUser.getOpenId()),
                redisUser.getOpenId());


        //TODO: 后续添加
//        insertUserLoginLog(redisUser, sessionKey, "login", userQueryPara.getIp());// 处理登录日志

//        userQueryResult.setCode(BusinessCodes.SUCCESS);

        userQueryResult.setSessionKey(sessionKey);

        // TODO: 后期添加
//        try {
//            pushRelationService.saveUserDevice(userQueryPara.getRegistrationId(), redisUser.getUserId());
//        } catch (Exception e) {
//            log.error(
//                    "save user device relation falure, registrationId:" + userQueryPara.getRegistrationId() + " userId:"
//                            + redisUser.getUserId(), e);
//        }
        return userQueryResult;
    }

    private Long initUser(String openId) {
        long sequenceId = baseService.getNextSequence("TS_USER");
        // 初始化user对象
        User user = new User();
        user.setMobileNo("10000000000");  //表示从微信系统得到的
        user.setPassword(openId);  //使用openid作为password
        user.setStatus("0");// state=0 正常，其他不正常
        user.setUpdateDate(new Date());// 修改时间
        user.setUserId(sequenceId);
        int countUser = userDAO.insertUser(user);// 保存对象

        if (countUser > 0) {
            // 插入账户
            Account account = initAccount(user);
//           int tem = accountDao.insertAccount(account);
//
//            // 插入鸟币账户
            BirdCoinAccount birdCoinAccount = initBirdCoinAccount(user, account);
            accountDao.insertBirdCoinAccount(birdCoinAccount);
//            // 插入用户等级
            UserLevel userLevel = initUserLevel(user.getUserId());
            accountDao.insertUserLevel(userLevel);
        }
        return sequenceId;
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

    private BirdCoinAccount initBirdCoinAccount(User user, Account account) {
        BirdCoinAccount birdCoinAccount = new BirdCoinAccount();
        int sequenceId = baseService.getNextSequence("TT_BIRD_COIN_ACCOUNT").intValue();
        birdCoinAccount.setId(sequenceId);
        birdCoinAccount.setBrdAccountId(String.valueOf(sequenceId));// // TODO:
        // 2015/12/16
        // 待定，这个字段放什么值
        // liuyw
        birdCoinAccount.setUserId(user.getUserId());
        birdCoinAccount.setAccountId(account.getId());
        birdCoinAccount.setTotalAmount(0);
        birdCoinAccount.setBalance(0);
        birdCoinAccount.setCreateDate(new Date());
        birdCoinAccount.setUpdateDate(new Date());
        return birdCoinAccount;
    }


}
