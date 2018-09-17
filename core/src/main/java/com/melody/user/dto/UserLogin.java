package com.melody.user.dto;

/**
 * Created by liuyw on 2015/11/11.
 */

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录日志
 */
@Data
public class UserLogin implements Serializable {

	private static final long serialVersionUID = -4300081059707242003L;
	private long id;
	private long userId;
	private String userName;
	private String mobileNo;
	private String locationInfo;
	private String primaryPwd;
	private String newPwd;
	private String loginType;
	private String loginIp;
	private String errorReson;
	private String loginStatus;
	private String sessionKey;
	private Date loginDate;


}
