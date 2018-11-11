package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.api.AdminSPUService;
import com.melody.admin.api.SysPermService;
import com.melody.admin.dto.SysPerm;
import com.melody.dao.AdminSKUMapper;
import com.melody.dao.AdminSPUMapper;
import com.melody.dao.AdminUserMapper;
import com.melody.dao.SysPermMapper;
import com.melody.product.dto.*;
import com.melody.system.dto.SysCustomerLevel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(group = "adminSKUService", timeout = 10000)
public class AdminSKUServiceImpl implements AdminSKUService {

    @Autowired
    AdminSKUMapper adminSKUMapper;

    @Autowired
    AdminSPUMapper adminSPUMapper;

    @Autowired
    BaseServiceImpl baseService;


    @Autowired
    AdminUserMapper adminUserMapper;

//    @Override
//    public String addSKU(SKU sku) {
//        return null;
//    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String addSKU(SKU sku) {

//        SKU sku = new SKU();
//        BeanUtils.copyProperties(skuEnter, sku);
        long skuId = baseService.getNextSequence("TT_SKU");
        sku.setId(skuId);

        // SKU NO
        String skuNo = "SKU" + skuId;
        sku.setSkuNo(skuNo);

        sku.setStatus("1"); // 新建，有效


        int insertResult = adminSKUMapper.insert(sku);

        // 添加属性成功后，在添加特性
        if (insertResult == 1) {
            if (sku.getSkuImageList() != null) {
                int seqId = 0;
                for (SkuImage skuImage : sku.getSkuImageList()) {
                    long skuImageId = baseService.getNextSequence("TR_SKU_IMAGE");
                    skuImage.setIsMain(seqId == 0 ? 1 : 0);
                    skuImage.setPicSeq(seqId++);
                    skuImage.setId(skuImageId);
                    skuImage.setSkuNo(skuNo);
                    adminSKUMapper.insertSKUImage(skuImage);
                }
            }

            // 增加库存
            if(sku.getSkuInventory() !=null) {
                SkuInventory inventory = sku.getSkuInventory();
                long inventoryId = baseService.getNextSequence("TR_SKU_INVENTORY");
                inventory.setId(inventoryId);
                inventory.setSkuNo(skuNo);
                adminSKUMapper.insertSKUInventory(inventory);
            }

            // 增加价格
            if(sku.getSkuPriceEnter() !=null) {
                SkuPriceEnter skuPriceEnter = sku.getSkuPriceEnter();
                SkuPrice skuPrice = new SkuPrice();
                BeanUtils.copyProperties(skuPriceEnter, skuPrice);
                long skuPriceId = baseService.getNextSequence("TR_SKU_PRICE");
                skuPrice.setId(skuPriceId);
                skuPrice.setSkuNo(skuNo);
                adminSKUMapper.insertSKUPrice(skuPrice);

                // 添加会员价格
                List<SysCustomerLevel> customerLevelList =  adminUserMapper.getCustomerLevel();
               for(int i=0;i<customerLevelList.size();i++){
                    UserSkuDiscount userSkuDiscount =  new UserSkuDiscount();
                    long userSkuDiscountId = baseService.getNextSequence("TR_USER_SKU_DISCOUNT");
                   userSkuDiscount.setId(userSkuDiscountId);
                   userSkuDiscount.setDiscount(skuPriceEnter.getDiscountPriceList().get(i));
                   userSkuDiscount.setDiscount(100D);
                   userSkuDiscount.setUserLevelId(customerLevelList.get(i).getLevelId());
                   userSkuDiscount.setSkuNo(skuNo);
                   userSkuDiscount.setStatus("1");
                   adminSKUMapper.insertUserSKUDiscount(userSkuDiscount);
              }
            }
            // 增加SKU 特别的属性
            if (sku.getSkuAttr() !=null) {
                long skuAttrId = baseService.getNextSequence("TR_SKU_ATTR");
                SkuAttr skuAttr = sku.getSkuAttr();
                skuAttr.setSkuNo(sku.getSkuNo());
                skuAttr.setId(skuAttrId);
                adminSKUMapper.insertSKUAttr(skuAttr);
            }

        } else {
            // TODO: add fail later
        }
        return skuNo;
    }






    @Override
    public Page<SKU> querySKUList(Page page) {
        int currentPage = page.getCurrent() - 1; // current为1， 所以往后减一位
        int pageSize = page.getSize();
        int totalCount = adminSKUMapper.countAllSKU();// 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        String validStatus = "1";
        List<SKU> records = adminSKUMapper.querySKUList(pager.getOffset(), pageSize, validStatus);

        Page<SKU> brandPage = new Page<SKU>();
        brandPage.setRecords(records);
        brandPage.setSize(pageSize);
        brandPage.setCurrent(currentPage);
        brandPage.setTotal(totalCount);
        return brandPage;
    }

    @Override
    public int deleteBySPUId(Long spuId) {
        int result = adminSKUMapper.deleteBySPUId(spuId);
        return result;
    }

    // 产品状态位设置为-1， 不被搜索到
    @Override
    public int deleteById(String skuNo) {
        String status = "-1"; //表示删除
        int result = adminSKUMapper.updateSKU(skuNo, status);
        return result;
    }


    @Override
    public SKU querySKUBySkuNo(String skuNo) {
        SKU sku = adminSKUMapper.querySKUBySkuNo(skuNo);

        // sku attr属性
        if (sku != null) {
            SkuAttr skuAttr = adminSKUMapper.getSkuAttrBySkuNo(skuNo);
            if (skuAttr != null) {
                sku.setSkuAttr(skuAttr);
            }

            // 展示所有的属性，便于显示
            List<SpuAttr> spuAttrList = adminSPUMapper.getSpuAttrBySpuCode(sku.getSpuCode());
            sku.setSpuAttrList(spuAttrList);

        }


        return sku;
    }

}

