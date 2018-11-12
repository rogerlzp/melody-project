package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminBrandService;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.dto.SysUser;
import com.melody.annotation.PermInfo;
import com.melody.product.dto.*;
import com.melody.util.PageUtils;
import com.melody.vo.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


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

        int brandId = adminBrandService.addBrand(brandObj);
        return Json.result(oper, brandId != -1 ? true : false)
                .data("brandId", brandId);
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

    @PermInfo("删除Brand")
    @RequiresPermissions("a:sku:brand:del")
    @DeleteMapping
    public Json delete(@RequestBody String body) {

        String oper = "delete brand";
        log.info("{}, body: {}", oper, body);

        JSONObject jsonObj = JSON.parseObject(body);
        Integer brandId = jsonObj.getInteger("id");
        if (brandId == null) {
            return Json.fail(oper, "无法删除SPU：参数为空（skuNo）");
        }

        //限制：不能删当前登录用户
        boolean success = adminBrandService.deleteBrandById(brandId) == 1 ? true : false;
        log.info("delete result: " + success);
        return Json.result(oper, success);
    }


    @PatchMapping("/update")
    public Json updateBrand(@RequestBody String body) {
        String oper = "update brand";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);
        Brand brandObj = JSON.parseObject(body, Brand.class);
        boolean success = adminBrandService.updateBrand(brandObj) == 1 ? true : false;

        return Json.result(oper, success).data("updated", "succeed");
    }



    @PermInfo("查询所有品牌")
    @RequiresPermissions("a:sku:brand:detail")
    @GetMapping("/detail")
    public Json detail(Integer brandId) {
        String oper = "query project detail";
        log.info("{}, body: {}", oper, brandId);
        if (brandId == null) {
            return Json.fail(oper, "无法获取品牌：参数为空（brandId）");
        }

        Brand brand = adminBrandService.getBrandById(brandId);
        return Json.succ(oper).data("brand", brand);
    }



}