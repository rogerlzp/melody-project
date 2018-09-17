package com.melody.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liuyw on 2015/12/16.
 */
@Data
public class BirdCoinAccount implements Serializable {

	private static final long serialVersionUID = 9030081724226571553L;
	private Integer id;
	private String brdAccountId;// 鸟币账号
	private Long userId;// 用户编号
	private Long accountId;// 账户编号
	private Integer totalAmount;// 总额
	private Integer balance;// 余额
	private Date createDate;// 创建时间
	private Date updateDate;// 更新时间

}
