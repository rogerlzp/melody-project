package com.melody.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserWx implements Serializable {

    private static final long serialVersionUID = -4074320180909113334L;

    private Long id;

    private Long userId;

    private String openId;

    private String nickname;

    private Boolean sex;

    private String city;

    private String country;

    private String province;

    private String language;

    private String headimgurl;

    private Long subscribeTime;

    private Integer unionId;

    private String remark;

    private String groupid;

    private String tagidList;

    private Date createDate;


}