package com.melody.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 3230925640627080309L;
    private Long id;
    private Long userId;// 用户id
    private double totalAsset;// 总资产
    private double usableBalance;// 可用余额
    private double frozenAmount;// 冻结金额 投标中、提现中或提取处理中资金会暂时冻结，满标或资金解冻后资金会自动解冻
    private Date createDate;
    private Date updateDate;
    private double eggToken;  //蛋币

}