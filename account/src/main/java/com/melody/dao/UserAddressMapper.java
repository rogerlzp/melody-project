package com.melody.dao;


import com.melody.user.dto.User;
import com.melody.user.dto.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    int resetDefaultAddress(@Param(value = "userId") Long userId);

    List<UserAddress> getUserAddress(@Param(value = "openId") String openId);

    UserAddress getDefaultAddressByUserId(@Param(value = "userId") Long userId);

}