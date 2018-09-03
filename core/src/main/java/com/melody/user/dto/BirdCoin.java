package com.melody.user.dto;

import java.io.Serializable;

public class BirdCoin implements Serializable{


	private static final long serialVersionUID = 7359698106983435188L;
	private double amount;
	private double total;
	private String createDate;
	private String typeName;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
