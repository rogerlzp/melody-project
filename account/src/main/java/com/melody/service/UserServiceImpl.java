package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.melody.common.utils.StringUtils;
import com.melody.common.utils.crypto.CryptoUtils;
import com.melody.dao.UserDAO;

import com.melody.exception.BaseException;
import com.melody.exception.TxException;
import com.melody.result.ErrorCode;
import com.melody.user.api.UserService;
import com.melody.user.dto.User;
import com.melody.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户api接口
 *
 * @author konghang
 */
@Component
@Service(group = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDao;

    /**
     * 查询用户
     *
     * @param user
     * @return
     */
    @Override
    public User selectUserBy(User user) {

//        return userDao.selectUserBy(user);
        return null;
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     * @throws TxException
     */
    @Transactional(rollbackFor = TxException.class)
    @Override
    public Integer saveUser(User user) throws TxException {
        User search = new User();
        //判断是否重名

        search.setUserName(user.getUserName());
//        if (!StringUtils.isEmpty(userDao.selectUserBy(search))) {
//            throw new BaseException(ErrorCode.USER_NAME_EXIST);
//        }
        search.setUserName(null);
        search.setEmail(user.getEmail());
//        if (!StringUtils.isEmpty(userDao.selectUserBy(search))) {
//            throw new BaseException(ErrorCode.USER_EMAIL_EXIST);
//        }
        //取到加密种子
        String salt = CryptoUtils.getSalt();
        user.setUserName(user.getUserName().trim());
        user.setPassword(user.getPassword().trim());
        //生成新的加密密码
        String hashPassword = CryptoUtils.passwordMD5(user.getPassword() + salt + user.getUserName());
        user.setSalt(salt);
        user.setPassword(hashPassword);
//        user.setRefUserId(StringUtils.getUUID());
        user.setCreateDate(DateUtils.getUnixStamp());
//        user.setSex((byte) 1);

//        if (userDao.insertSelective(user) < 1) {
//            throw new TxException(ErrorCode.USER_CREATE_ERROR);
//        }
        /*UserRole userRole = new UserRole();
        userRole.setRefUserId(user.getRefUserId());
        userRole.setRoleId(1);
        if(userRoleDao.insertSelective(userRole) < 1){
            throw new TxException(ErrorCode.USER_ROLE_CREATE_ERROR);
        }*/
        return 1;
    }
}
