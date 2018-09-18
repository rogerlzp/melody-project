package com.melody.web.vo;

import lombok.Data;

/**
 * Created by liuyw on 2015/11/19.
 */
@Data
public class BaseForm {
    private String clientType;

    private String sessionKey;

    private String openId; // 用户ID

}
