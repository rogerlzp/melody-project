package com.melody.user.dto;

/**
 * Created by liuyw on 2015/11/11.
 */

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录日志
 */
public class UserLogin implements Serializable {

	private static final long serialVersionUID = -4300081059707242003L;
	private long id;
	private long userId;
	private String userName;
	private String loginMobileNo;
	private String locationInfo;
	private String primaryPwd;
	private String newPwd;
	private String logType;
	private String loginIp;
	private String errorReson;
	private String loginStatus;
	private String sessionKey;
	private Date loginDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginMobileNo() {
		return loginMobileNo;
	}

	public void setLoginMobileNo(String loginMobileNo) {
		this.loginMobileNo = loginMobileNo;
	}

	public String getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(String locationInfo) {
		this.locationInfo = locationInfo;
	}

	public String getPrimaryPwd() {
		return primaryPwd;
	}

	public void setPrimaryPwd(String primaryPwd) {
		this.primaryPwd = primaryPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getErrorReson() {
		return errorReson;
	}

	public void setErrorReson(String errorReson) {
		this.errorReson = errorReson;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
}
