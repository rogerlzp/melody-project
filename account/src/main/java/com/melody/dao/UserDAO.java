package com.melody.dao;


import com.melody.service.BaseMapper;
import com.melody.user.dto.User;
import com.melody.user.dto.UserLogin;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO extends BaseMapper<User> {

//    //插入profile
//    int insertProfile(UserProfile userProfile);
//
//    //更新profile
//    int updateProfile(UserProfile userProfile);
//
//    //获取profileId
//    Long getProfileId(@Param(value = "userId") long userId);
//    //
//    UserProfile getProfile(@Param(value = "userId") long userId);
//
//
//    //插入profile
//    int insertIOSProfile(UserIOSProfile userIOSProfile);
//
//    //更新profile
//    int updateIOSProfile(UserIOSProfile userIOSProfile);
//
//    //获取profileId
//    Long getIOSProfileId(@Param(value = "userId") long userId);
//    //
//    UserIOSProfile getIOSProfile(@Param(value = "userId") long userId);


    //检查用户，基本资料是否提交
//    int getIOSProfileSubmit(@Param(value = "userId") long userId);
//
//    //检查用户，基本资料是否提交
//    int updateIOSProfileSubmit(@Param(value = "userId") long userId);
//
//    //检查用户，申请是否提交
//    int getIOSApplySubmit(@Param(value = "userId") long userId);
//
//    int updateIOSApplySubmit(@Param(value = "userId") long userId);

    int insertUserSubmit(@Param(value = "id") long id, @Param(value = "userId") long userId);
    String getSubmitTime(@Param(value = "userId") long userId);

    int insertUser(User user);

    int countUser(@Param(value = "mobileNo") String mobileNo);

    int updateUserPwd(@Param(value = "mobileNo") String mobileNo, @Param(value = "newPwd") String newPwd);

    int retrieveUserPwd(@Param(value = "mobileNo") String mobileNo, @Param(value = "newPwd") String newPwd);

    User getUserByMobileNo(@Param(value = "mobileNo") String mobileNo);

    int updateUserExperience(@Param(value = "userId") long userId);

    int updateUser(User user);

    int updateUserBankAuthStatus(@Param(value = "userId") String userId);

    User getUserByUmpayUserNo(@Param(value = "umpayUserNO") String umpayUserNO);//根据联动优势的userId去查询相应的人

    int insertUserLoginLog(UserLogin userLogin);//插入登录日志

    UserLogin findLastUserLoginLog(@Param(value = "userId") String userId);//查询最后一次登录日志

    int updatLastUserLoginLogById(@Param(value = "id") Long id, @Param(value = "status") String status);//修改日志的状态为已过期

    int findUserByIdentityCode(@Param(value = "identityCode") String identityCode, @Param(value = "userName") String userName);//查询该身份证是否存在

//    UserInfo getUserInfoByUserId(@Param(value = "userId") long userId);//获取用户对象的方法
//
//	List<UserInfoList> findUserInfoList(UserInfoListEnter userInfoListEnter);
//
//	int findUserInfoListCount(UserInfoListEnter userInfoListEnter);

    User getUserById(@Param(value = "userId") long userId);

    void updateUserStaffFlag(@Param(value = "userId") long userId);

//    UserInfo getUserStaffInfoByUserId(@Param(value = "userId") long fatherId);

    void updateUserLevel(@Param(value = "userId") long id, @Param(value = "fromLevel") String fromLevel, @Param(value = "toLevel") String toLevel);

//    public void insertUserExt(UserExt userExt);//插入注册用户扩展信息
}


