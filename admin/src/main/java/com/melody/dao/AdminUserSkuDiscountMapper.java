package com.melody.dao;

import com.melody.product.dto.UserSkuDiscount;

public interface AdminUserSkuDiscountMapper {

    int deleteUserByUserId(Long id);

    int insert(UserSkuDiscount record);

    int insertSelective(UserSkuDiscount record);

    UserSkuDiscount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSkuDiscount record);

    int updateByPrimaryKey(UserSkuDiscount record);

}