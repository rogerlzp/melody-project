package com.melody.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public String mobileNo;// 手机号
    public String nickname;
    public String salt;
    public String password;
    public int gender; //性别
    public String email;
    public String headImage;
    public String createIp;
    public Long createDate;
    private String certification;// 实名认证
    private String deviceToken;// 手机推送码
    private String cardId;// 身份证关联id
    private String status;// 状态
    private String referralCode;// 推荐码
    private Date updateDate;// 修改时间

    private String guestType;// 客户类型
    private String userLevelId;// 客户等级 USER_LEVEL_ID

    private int isExperience;// '0：未体验；1：已体验',
    private int isStaff;// 是否内部员工
    private int isFirstOrder;// 是否首次下单
    private int isHasOrder;

    public String description; // 用户描述
    public String slogon; // 格言
    private Date birthday;// 生日


    private Long userId;

    private String userName; // 姓名

    private String roleName; // 姓名
    private int roleId; // 姓名

    private String userPwd;

    private String idCard;

    private Date regDate;


    public User() {
    }

}