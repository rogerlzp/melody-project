package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminBrandService;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.dto.SysUser;
import com.melody.annotation.PermInfo;
import com.melody.product.dto.Brand;
import com.melody.util.PageUtils;
import com.melody.vo.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@PermInfo(value = "创造产品模块", pval = "a:sku")
@RestController
@RequestMapping("/adminBrand")
public class AdminBrandController {

    private static final Logger log = LoggerFactory.getLogger(AdminBrandController.class);


    @Reference(group = "adminBrandService", timeout = 10000)
    private AdminBrandService adminBrandService;


    @PermInfo("添加系统用户")
    @RequiresPermissions("a:sku:brand:add")
    @PostMapping("/add")
    public Json add(@RequestBody String brandStr) {

        String oper = "add brand";
        log.info("{}, body: {}", oper, brandStr);

        Brand brandObj = JSON.parseObject(brandStr, Brand.class);

        if (StringUtils.isEmpty(brandObj.getBrandName())) {
            return Json.fail(oper, "品牌名不能为空");
        }

        int success = adminBrandService.addBrand(brandObj);
        return Json.result(oper, success == 1 ? true : false)
                .data("brand", brandObj.getBrandName());
    }


    @PermInfo("查询所有品牌")
    @RequiresPermissions("a:sku:brand:query")
    @PostMapping("/query")
    public Json query(@RequestBody String body) {
        String oper = "query brand";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);

        Page<Brand> page = adminBrandService.queryBrandList(PageUtils.getPageParam(json));

        return Json.succ(oper).data("page", page);

    }


}