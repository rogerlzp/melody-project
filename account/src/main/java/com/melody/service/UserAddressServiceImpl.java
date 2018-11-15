package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.melody.base.GeneralResult;
import com.melody.common.constant.BusinessCodes;
import com.melody.common.constant.RedisCodes;
import com.melody.dao.UserAddressMapper;
import com.melody.dao.UserDAO;
import com.melody.redis.RedisCache;
import com.melody.user.api.LoginService;
import com.melody.user.api.UserAddressService;
import com.melody.user.dto.UserAddress;
import com.melody.user.dto.UserAddressResult;
import com.melody.user.dto.UserAddressSaveResult;
import com.melody.user.dto.UserWx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(group = "userAddressService", timeout = 20000)
public class UserAddressServiceImpl implements UserAddressService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserDAO userDAO;
    @Autowired
    RedisCache redisCache;

    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    BaseServiceImpl baseService;

    //返回UserAddress id
    @Override
    public GeneralResult saveUserAddress(UserAddress userAddress) {
        long sequenceId = baseService.getNextSequence("TR_USER_ADDRESS");
        userAddress.setId(sequenceId);

        // TODO: 检查是不是过期
        // 更新默认页面

        UserWx redisUser = redisCache.getObject(RedisCodes.WX_USER + userAddress.getOpenId(), UserWx.class);
        if(redisUser!=null){
            userAddress.setUserId(redisUser.getUserId());
        }

        if (userAddress.getIsDefault() == 1) {
            int updateResult = userAddressMapper.resetDefaultAddress(userAddress.getUserId());
        }
        // 根据openId先查出UserId
        int id = userAddressMapper.insert(userAddress);

        UserAddressSaveResult userAddressSaveResult = new UserAddressSaveResult();
        if (id == 1) {
            BeanUtils.copyProperties(userAddress, userAddressSaveResult);
        }
//        userAddressSaveResult.setCode(BusinessCodes.SUCCESS);

        return GeneralResult.isOk().data(userAddressSaveResult);
    }

    @Override
    public GeneralResult getUserAddressByOpenId(String openId) {
        UserAddressResult userAddressResult = new UserAddressResult();

        List<UserAddress> userAddressList = userAddressMapper.getUserAddress(openId);


        userAddressResult.setRecords(userAddressList);

        return GeneralResult.isOk().data(userAddressResult);

    }

    @Override
    public GeneralResult getUserAddressById(Long id) {
        UserAddressSaveResult userAddressSaveResult = new UserAddressSaveResult();

        UserAddress userAddress = userAddressMapper.selectByPrimaryKey(id);


        BeanUtils.copyProperties(userAddress, userAddressSaveResult);
        return GeneralResult.isOk().data(userAddressSaveResult);

    }

    @Override
    public UserAddress getDefaultAddressByUserId(Long userId) {
        return userAddressMapper.getDefaultAddressByUserId(userId);
    }
}