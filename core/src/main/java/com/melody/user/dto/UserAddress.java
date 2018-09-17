package com.melody.user.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserAddress implements Serializable{

    private static final long serialVersionUID = -5434836707409131531L;

    private Long id;

    private Long userId;

    private String openId;

    private String addressDetail;

    private int isDefault;

    private String receiverName;

    private String receiverPhone;

    private String zipcode;

    private Date createDate;

    private String province;

    private String city;

    private String area;


}