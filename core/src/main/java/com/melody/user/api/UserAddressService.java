package com.melody.user.api;

import com.melody.annotation.Role;
import com.melody.base.GeneralResult;
import com.melody.exception.TxException;
import com.melody.user.dto.User;
import com.melody.user.dto.UserAddress;
import com.melody.user.dto.UserAddressResult;
import com.melody.user.dto.UserAddressSaveResult;

import java.util.List;

public interface UserAddressService {

    @Role
    GeneralResult saveUserAddress(UserAddress userAddress);



    GeneralResult getUserAddressByOpenId(String openid);

//    @Role
//    List<UserAddress> getUserAddressByUserId(UserAddress userAddress);

    GeneralResult getUserAddressById(Long id);

    UserAddress getDefaultAddressByUserId(Long userId);

}
