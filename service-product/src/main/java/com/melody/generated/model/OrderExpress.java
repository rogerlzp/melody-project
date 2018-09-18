package com.melody.generated.model;

import java.util.Date;

public class OrderExpress {
    private Long id;

    private String orderNo;

    private String receiverName;

    private String addressDetail;

    private String receiverPhone;

    private String zipcode;

    private String develieryCompany;

    private Date develieryTime;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}