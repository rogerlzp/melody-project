package com.melody.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liuyw on 2015/12/16.
 */
public class BirdCoinAccount implements Serializable {

	private static final long serialVersionUID = 9030081724226571553L;
	private Integer id;
	private String brdAccountId;// 鸟币账号
	private long userId;// 用户编号
	private Integer accountId;// 账户编号
	private double totalAmount;// 总额
	private double balance;// 余额
	private Date createDate;// 创建时间
	private Date updateDate;// 更新时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrdAccountId() {
		return brdAccountId;
	}

	public void setBrdAccountId(String brdAccountId) {
		this.brdAccountId = brdAccountId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
