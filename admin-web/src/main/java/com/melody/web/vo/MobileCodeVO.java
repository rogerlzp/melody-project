package com.melody.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ApiModel(value = "userVo对象", description = "用户对象userVo")

public class MobileCodeVO {


//    private String idCard;//身份证号码
//
//    private String appVersion;
//    private String channel;
//    private String osVersion;
//    private String app_client_id;

    @NotBlank
    @Length(min = 11, max = 11)
    @ApiModelProperty(value = "手机号码", name = "mobileNo")
    @Getter
    @Setter
    private String mobileNo;


    @ApiModelProperty(value = "发送类型", name = "sendType")
    @Getter
    @Setter
    private String sendType;


    @ApiModelProperty(value = "手机验证码", name = "mobileCode")
    @Getter
    @Setter
    private String mobileCode;

    private String clientType;
    private String appVersion;
    private String channel;
    private String osVersion;
    private String app_client_id;

    private String pictureCode;//图形验证码


    public String getClientType() {
        return this.clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getApp_client_id() {
        return this.app_client_id;
    }

    public void setApp_client_id(String app_client_id) {
        this.app_client_id = app_client_id;
    }

    public String getPictureCode() {
        return this.pictureCode;
    }

    public void setPictureCode(String pictureCode) {
        this.pictureCode = pictureCode;
    }
}
