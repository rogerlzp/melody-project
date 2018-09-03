package com.melody.user.dto;

import com.melody.base.GeneralEnter;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterEnter extends GeneralEnter implements Serializable {
    private static final long serialVersionUID = 8697015303404646148L;
    public String machineNo;
    public String mobileNo;
    public String password;
    public String mobileCode;
    public String partnerMobile;
    public String readAndAgree;
    public String registrationId;
    public String identityCode;
    public String userName;
    public String channel;



}
