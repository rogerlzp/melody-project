package com.melody.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 用户vo
 * @author konghang
 */
@ApiModel(value="userVo对象",description="用户对象userVo")
public class UserVO {

    @NotBlank
    @Length(max = 10)
    @ApiModelProperty(value="用户名",name="username",example="konghang")
    @Getter
    @Setter
    private String username;


    @NotBlank
    @Length(max = 11)
    @ApiModelProperty(value="手机号",name="mobileNo",example="15800911536")
    @Getter
    @Setter
    private String mobileNo;

    @NotBlank
    @Length(max = 6)
    @ApiModelProperty(value="验证码",name="mobileCode",example="8880")
    @Getter
    @Setter
    private String mobileCode;


    @NotBlank
    @Size(max = 20)
    @ApiModelProperty(value="用户密码",name="password")
    @Getter
    @Setter
    private String password;


    @NotBlank
    @Size(max = 2)
    @ApiModelProperty(value="已经同意",name="readAndAgree")
    @Getter
    @Setter
    private String readAndAgree;


    @ApiModelProperty(value="邮箱",name="email",example="konghang@test.com")
    @Pattern(regexp = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?", message = "邮箱格式不正确")
    @Getter
    @Setter
    private String email;

    @ApiModelProperty(value="用户昵称",name="nickname",example="孔航")
    @Getter
    @Setter
    private String nickname;

    @Override
    public String toString() {
        return super.toString();
    }
}
