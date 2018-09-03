package com.melody.generated.model;

import java.util.Date;

public class OrderExpress {
    private Long id;

    private String orderCode;

    private Integer userName;

    private String address;

    private String contactPhone;

    private String zipcode;

    private String develieryCompany;

    private Date develieryTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getUserName() {
        return userName;
    }

    public void setUserName(Integer userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getDevelieryCompany() {
        return develieryCompany;
    }

    public void setDevelieryCompany(String develieryCompany) {
        this.develieryCompany = develieryCompany == null ? null : develieryCompany.trim();
    }

    public Date getDevelieryTime() {
        return develieryTime;
    }

    public void setDevelieryTime(Date develieryTime) {
        this.develieryTime = develieryTime;
    }
}