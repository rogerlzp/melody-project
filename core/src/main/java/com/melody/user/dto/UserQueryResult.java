package com.melody.user.dto;



import com.melody.base.GeneralResult;

import lombok.Data;

import java.util.List;

@Data
public class UserQueryResult extends GeneralResult {

    private static final long serialVersionUID = -4074348732920836434L;

    private List<User> returnlist; // 查询返回列表

    private String sessionKey;//sessioney
    private String verifyCode;//验证码
    private String openid;// 微信用户的openid
}