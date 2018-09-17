package com.melody.dao;


import com.melody.user.dto.UserWx;
import org.apache.ibatis.annotations.Param;

public interface UserWxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserWx record);

    int insertSelective(UserWx record);

    UserWx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserWx record);

    int updateByPrimaryKey(UserWx record);

    // 检查是否存在OpenId
    UserWx selectByOpenId(@Param(value = "openId") String openId);

}