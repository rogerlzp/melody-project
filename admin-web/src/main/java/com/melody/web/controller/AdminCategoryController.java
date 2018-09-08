package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminBrandService;
import com.melody.admin.api.AdminCategoryService;
import com.melody.annotation.PermInfo;
import com.melody.product.dto.Brand;
import com.melody.product.dto.Category;
import com.melody.util.PageUtils;
import com.melody.vo.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@PermInfo(value = "创造产品模块", pval = "a:sku")
@RestController
@RequestMapping("/adminCategory")
public class AdminCategoryController {

    private static final Logger log = LoggerFactory.getLogger(AdminCategoryController.class);


    @Reference(group = "adminCategoryService")
    private AdminCategoryService adminCategoryService;


    @PermInfo("添加系统用户")
    @RequiresPermissions("a:sku:category:add")
    @PostMapping("/add")
    public Json add(@RequestBody String brandStr) {

        String oper = "add brand";
        log.info("{}, body: {}", oper, brandStr);

        Category brandObj = JSON.parseObject(brandStr, Category.class);

//        if (StringUtils.isEmpty(brandObj.getBrandName())) {
//            return Json.fail(oper, "品牌名不能为空");
//        }

        int success = adminCategoryService.addCategory(brandObj);
        return Json.result(oper, success == 1 ? true : false)
                .data("category", brandObj.getCategoryName());
    }


    @PermInfo("查询所有品牌")
    @RequiresPermissions("a:sku:category:query")
    @PostMapping("/query")
    public Json query(@RequestBody String body) {
        String oper = "query brand";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);

        Page<Category> page = adminCategoryService.queryCategoryList(PageUtils.getPageParam(json));


        return Json.succ(oper).data("page", page);

    }


}