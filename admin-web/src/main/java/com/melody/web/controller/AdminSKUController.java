package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.api.AdminSPUService;
import com.melody.admin.api.SysPermService;
import com.melody.annotation.PermInfo;
import com.melody.product.dto.*;
import com.melody.util.PageUtils;
import com.melody.vo.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@PermInfo(value = "创造产品模块", pval = "a:sku")
@RestController
@RequestMapping("/adminSKU")
public class AdminSKUController {

    private static final Logger log = LoggerFactory.getLogger(AdminSKUController.class);


    @Reference(group = "adminSKUService", timeout = 10000)
    private AdminSKUService adminSKUService;

    @Reference(group = "adminSPUService", timeout = 10000)
    private AdminSPUService adminSPUService;

    @PermInfo("添加SPU")
    @RequiresPermissions("a:sku:sku:add")
    @PostMapping("/add")
    public Json add(@RequestBody String brandStr) {

        String oper = "add sku";
        log.info("{}, body: {}", oper, brandStr);


        SKU brandObj = JSON.parseObject(brandStr, SKU.class);

        // 因为传来的Image 只有 picUrl, 不能自动转化为SkuImage, 所以手动转化一下，在添加到 sku中
        JSONObject jsonObject = JSON.parseObject(brandStr);
        JSONArray jsonArray = jsonObject.getJSONArray("skuImageList");
        List<SkuImage> skuImageList = new ArrayList<>();
        if (jsonArray != null) {
            for (Object obj : jsonArray) {
                JSONObject jsonObject2 = (JSONObject) obj;
                String picUrl = jsonObject2.getString("picUrl");
                SkuImage skuImage = new SkuImage();
                skuImage.setPicUrl(picUrl);
                skuImageList.add(skuImage);
            }
            brandObj.setSkuImageList(skuImageList);
        }

        JSONObject skuAttrObj = jsonObject.getJSONObject("skuAttributes");
        if (skuAttrObj != null) {
            SkuAttr skuAttr = JSONObject.parseObject(skuAttrObj.toString(), SkuAttr.class);
            brandObj.setSkuAttr(skuAttr);
        }


        // 获取库存

        if (jsonObject.getInteger("totalNum") != null) {
            SkuInventory inventory = new SkuInventory();
            inventory.setTotalNum(jsonObject.getInteger("totalNum"));
            if (jsonObject.getInteger("sellableNum") != null) {
                inventory.setSellableNum(jsonObject.getInteger("sellableNum"));
            }
            if (jsonObject.getInteger("lockedNum") != null) {
                inventory.setLockedNum(jsonObject.getInteger("lockedNum"));
            }
            brandObj.setSkuInventory(inventory);

        }

        // 获取价格

        if (jsonObject.getDouble("listPrice") != null) {
            SkuPriceEnter skuPriceEnter = new SkuPriceEnter();
            skuPriceEnter.setListPrice(jsonObject.getDouble("listPrice"));

            if (jsonObject.getDouble("salePrice") != null) {
                skuPriceEnter.setSalePrice(jsonObject.getDouble("salePrice"));
            }
            if (jsonObject.getDouble("specialPrice") != null) {

                skuPriceEnter.setSpecialPrice(jsonObject.getDouble("specialPrice"));
            }
            if (jsonObject.getDouble("importPrice") != null) {

                skuPriceEnter.setImportPrice(jsonObject.getDouble("importPrice"));
            }
            if (jsonObject.getDouble("costPrice") != null) {
                skuPriceEnter.setCostPrice(jsonObject.getDouble("costPrice"));
            }

            if (jsonObject.getJSONArray("discountPrice") != null) {
                List<Double> discountPriceList =
                        JSON.parseArray(jsonObject.getJSONArray("discountPrice").toString(),
                                Double.class);
                skuPriceEnter.setDiscountPriceList(discountPriceList);
            }

            brandObj.setSkuPriceEnter(skuPriceEnter);
        }


        String skuNo = adminSKUService.addSKU(brandObj);
        return Json.result(oper, skuNo.equals("-1") ? false : true)
                .data("skuNo", skuNo);
    }


    @PermInfo("查询所有品牌")
    @RequiresPermissions("a:sku:sku:query")
    @PostMapping("/query")
    public Json query(@RequestBody String body) {
        String oper = "query brand";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);
//        String nick = json.getString("nick");

        Page<SKU> page = adminSKUService.querySKUList(PageUtils.getPageParam(json));
        return Json.succ(oper).data("page", page);
    }


    // TODO: 删除后，对应的SKU删除
    // 添加到日志里面
    @PermInfo("删除SPU")
    @RequiresPermissions("a:sku:spu:del")
    @DeleteMapping
    public Json delete(@RequestBody String body) {

        String oper = "delete sku";
        log.info("{}, body: {}", oper, body);

        JSONObject jsonObj = JSON.parseObject(body);
        String skuNo = jsonObj.getString("skuNo");
        if (StringUtils.isEmpty(skuNo)) {
            return Json.fail(oper, "无法删除SPU：参数为空（skuNo）");
        }

        //限制：不能删当前登录用户
        boolean success = adminSKUService.deleteById(skuNo) == 1 ? true : false;
        log.info("delete result: " + success);
        return Json.result(oper, success);
    }


    @PermInfo("查询所有品牌")
    @RequiresPermissions("a:sku:sku:detail")
    @GetMapping("/detail")
    public Json list(String skuNo) {
        String oper = "query sku";
        log.info("{}, body: {}", oper, skuNo);

        SKU sku = adminSKUService.querySKUBySkuNo(skuNo);

        return Json.succ(oper).data("sku", sku);
    }

}
