package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminBrandService;
import com.melody.admin.api.AdminFeatureService;
import com.melody.annotation.PermInfo;
import com.melody.product.dto.Brand;
import com.melody.product.dto.Feature;
import com.melody.util.PageUtils;
import com.melody.vo.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PermInfo(value = "创造产品模块", pval = "a:sku")
@RestController
@RequestMapping("/adminFeature")
public class AdminFeatureController {


    private static final Logger log = LoggerFactory.getLogger(AdminFeatureController.class);


    @Reference(group = "adminFeatureService", timeout = 10000)
    private AdminFeatureService adminFeatureService;


    @PermInfo("添加系统用户")
    @RequiresPermissions("a:sku:feature:add")
    @PostMapping("/add")
    public Json add(@RequestBody String brandStr) {

        String oper = "add brand";
        log.info("{}, body: {}", oper, brandStr);

        Feature brandObj = JSON.parseObject(brandStr, Feature.class);


//        if (StringUtils.isEmpty(brandObj.getBrandName())) {
//            return Json.fail(oper, "品牌名不能为空");
//        }

        int success = adminFeatureService.addFeature(brandObj);
        return Json.result(oper, success == 1 ? true : false)
                .data("feature", brandObj.getFeatureName());
    }


    @PermInfo("查询所有品牌")
    @RequiresPermissions("a:sku:feature:query")
    @PostMapping("/query")
    public Json query(@RequestBody String body) {
        String oper = "query brand";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);

        Page<Feature> page = adminFeatureService.queryFeatureList(PageUtils.getPageParam(json));
        return Json.succ(oper).data("page", page);
    }

    @PermInfo("查询所有品牌")
    @RequiresPermissions("a:sku:feature:query")
    @PostMapping("/getFeatureByCategory")
    public Json queryFeatureByCategory(@RequestBody String body) {
        String oper = "query feature by category";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);
        String categoryCode = json.getString("categoryCode");

        List<Feature> featureList = adminFeatureService.queryFeatureByCategoryCode(categoryCode);
        return Json.succ(oper).data("featureList", featureList);
    }


    // TODO: 删除后，对应的SKU删除
    // 添加到日志里面
    @PermInfo("删除SPU")
    @RequiresPermissions("a:sku:feature:del")
    @DeleteMapping
    public Json delete(@RequestBody String body) {

        String oper = "delete spu";
        log.info("{}, body: {}", oper, body);

        JSONObject jsonObj = JSON.parseObject(body);
        String id = jsonObj.getString("id");
        if (StringUtils.isEmpty(id)) {
            return Json.fail(oper, "无法删除SPU：参数为空（用户id）");
        }

        //限制：不能删当前登录用户
        boolean success = adminFeatureService.deleteById(Long.valueOf(id)) == 1 ? true : false;
        log.info("delete result: " + success);
        if (success) {
            adminFeatureService.deleteOptionById(Long.valueOf(id));
        }
        return Json.result(oper, success);
    }


}