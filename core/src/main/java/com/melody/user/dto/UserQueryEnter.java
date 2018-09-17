package com.melody.user.dto;


import com.melody.base.GeneralEnter;
import lombok.Data;

@Data
public class UserQueryEnter extends GeneralEnter {

	private static final long serialVersionUID = -5434836707491866717L;
	private String machineNo;// 机器唯一码
	private String mobileNo;// 手机号码
	private String password;// 密码
	private String pictureCode;// 图像验证码
	private String ip;// 用户登录的ip地址

	private String registrationId;


}
