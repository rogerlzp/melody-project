package com.melody.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户登录
 * Created by liuyw on 2015/12/25.
 */
@Data
public class UserLevel implements Serializable {

    private static final long serialVersionUID = -3838792339175482239L;
    private Long id;
    private String  level;



    private Integer userId;
    private Integer orderId;
    private Integer fatherId;
    private Integer grandFatherId;
    private Date createDate;
    private Date updateDate;


}
