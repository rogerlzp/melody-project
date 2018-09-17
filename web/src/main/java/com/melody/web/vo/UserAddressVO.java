package com.melody.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "UserAddressVO对象", description = "用户对象userVo")
public class UserAddressVO {

    @NotBlank
    @Length(max = 32)
    @ApiModelProperty(value = "openId", name = "openid", example = "o5Tau4oKx2ai5noHPxhcEN2PR0iE")
    @Getter
    @Setter
    private String openId;


    @NotBlank
    @Length(max = 255)
    @ApiModelProperty(value = "addressDetail", name = "address", example = "o5Tau4oKx2ai5noHPxhcEN2PR0iE")
    @Getter
    @Setter
    private String addressDetail;

    @ApiModelProperty(value = "id", name = "isDefault", example = "o5Tau4oKx2ai5noHPxhcEN2PR0iE")
    @Getter
    @Setter
    private int id;

    @ApiModelProperty(value = "isDefault", name = "isDefault", example = "o5Tau4oKx2ai5noHPxhcEN2PR0iE")
    @Getter
    @Setter
    private int isDefault;

    @ApiModelProperty(value = "province", name = "province", example = "o5Tau4oKx2ai5noHPxhcEN2PR0iE")
    @Getter
    @Setter
    private String province;

    @ApiModelProperty(value = "area", name = "area", example = "o5Tau4oKx2ai5noHPxhcEN2PR0iE")
    @Getter
    @Setter
    private String area;

    @ApiModelProperty(value = "city", name = "city", example = "o5Tau4oKx2ai5noHPxhcEN2PR0iE")
    @Getter
    @Setter
    private String city;

    @ApiModelProperty(value = "receiverName", name = "receiverName", example = "o5Tau4oKx2ai5noHPxhcEN2PR0iE")
    @Getter
    @Setter
    private String receiverName;

    @ApiModelProperty(value = "receiverPhone", name = "receiverPhone", example = "o5Tau4oKx2ai5noHPxhcEN2PR0iE")
    @Getter
    @Setter
    private String receiverPhone;


}

