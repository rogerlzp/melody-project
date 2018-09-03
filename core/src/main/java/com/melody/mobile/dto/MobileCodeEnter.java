package com.melody.mobile.dto;

import com.melody.base.GeneralEnter;
import lombok.Data;

import java.io.Serializable;

@Data
public class MobileCodeEnter extends GeneralEnter implements Serializable {

    private static final long serialVersionUID = -3753498201808281800L;

    private String mobileNo;
    private String certification;
    private String mobileCode;
    private String idCard;
    private String sendType;//1.注册手机验证码发送 [校验是否注册]  2.忘记密码 [校验是否实名制]  3.其他
    // {

}
