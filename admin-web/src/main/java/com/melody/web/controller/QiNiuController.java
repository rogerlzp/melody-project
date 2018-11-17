package com.melody.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.melody.annotation.PermInfo;
import com.melody.base.GeneralResult;
import com.melody.gateway.api.QiNiuTokenService;

import com.melody.gateway.dto.QiNiuTokenEnter;
import com.melody.gateway.dto.QiNiuTokenResult;
import com.melody.product.dto.Brand;
import com.melody.vo.Json;
import com.melody.web.util.JsonHelper;
import com.melody.web.vo.QiNiuForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

/**
 * Created by zhengpingli on 2017/4/11.
 */


@PermInfo(value = "Gateway", pval = "a:gateway")
@RestController
@RequestMapping("/qiniu")
public class QiNiuController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Reference(group = "qiNiuTokenService")
    private QiNiuTokenService qiNiuTokenService;


    @PermInfo("获取七牛Token")
    @RequiresPermissions("a:gateway:qiniuToken:get")
    @GetMapping("/upToken")
    public Json add(QiNiuForm qiNiuForm) {

        String oper = "get token";

        QiNiuTokenEnter qiNiuTokenEnter = new QiNiuTokenEnter();
        BeanUtils.copyProperties(qiNiuForm, qiNiuTokenEnter);

        QiNiuTokenResult result = qiNiuTokenService.getQiNiuToken(qiNiuTokenEnter);
//        int success = Integer.valueOf(result.getCode());
        return Json.result(oper, result.getUploadToken() != null ? true : false)
                .data("uploadToken", result.getUploadToken());
    }

}