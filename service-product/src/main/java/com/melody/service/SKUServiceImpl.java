package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.melody.annotation.Role;
import com.melody.base.GeneralEnter;
import com.melody.common.constant.BusinessCodes;
import com.melody.common.constant.SKUStatusEnum;
import com.melody.context.ServiceContext;
import com.melody.dao.SKUMapper;
import com.melody.dao.SPUMapper;
import com.melody.product.api.SKUService;
import com.melody.product.dto.*;
import com.melody.user.dto.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(group = "skuService")
public class SKUServiceImpl implements SKUService {


    @Autowired
    SKUMapper skuMapper;

    @Autowired
    SPUMapper spuMapper;

    @Override
    @Role
    public List<SKU> getSKU(GeneralEnter generalEnter) {
        List<SKU> skuList = skuMapper.querySKUList(0, 10, "1");

        return skuList;
    }

    @Override
    public List<SKU> getSKUWithoutUserInfo(GeneralEnter generalEnter) {
        List<SKU> skuList = skuMapper.querySKUList(0, 10, "1");

        return skuList;
    }

    @Override
    public SKUResult getSKUHomePage(GeneralEnter generalEnter) {
        SKUResult skuResult = new SKUResult();
        int currentPage = generalEnter.getCurrentPage(); // current为1， 所以往后减一位
        int pageSize = generalEnter.getPageSize();
        int totalCount = skuMapper.countAllSKU();// 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        List<SKU> skuList = skuMapper.querySKUList(pager.getOffset(), pager.getPageSize(), "1");
        for (SKU sku : skuList) {
            // 设置价格

        }
        skuResult.setRecords(skuList);

        skuResult.setTotalCount(totalCount);
        skuResult.setCode(BusinessCodes.SUCCESS);
        return skuResult;
    }

    @Override
    public SKUResult getSKUHomeBySpu(SKUEnter skuEnter) {
        SKUResult skuResult = new SKUResult();
        int currentPage = skuEnter.getCurrentPage(); // current为1， 所以往后减一位
        int pageSize = skuEnter.getPageSize();
        int totalCount = skuMapper.countAllSKUBySpu(skuEnter.getSpuCode());// 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        List<SKU> skuList = skuMapper.querySKUListBySpu(pager.getOffset(), pager.getPageSize(),
                skuEnter.getSpuCode(), "1");
        for (SKU sku : skuList) {
            // 设置价格
        }
        // get SPU result
        SPU spu = spuMapper.selectBySpuCode(skuEnter.getSpuCode());

        skuResult.setCategoryName(spu.getCategoryName());
        skuResult.setSpuName(spu.getSpuName());

        skuResult.setRecords(skuList);
        skuResult.setTotalCount(totalCount);
        skuResult.setCode(BusinessCodes.SUCCESS);
        return skuResult;
    }

    @Override
    public SKUDetailResult getSKUDetail(SKUEnter skuEnter) {
        SKU sku = skuMapper.selectByPrimaryKey(skuEnter.getSkuNo());

        SKUDetailResult skuDetailResult = new SKUDetailResult();
        // 添加图片
        BeanUtils.copyProperties(sku, skuDetailResult);

        List<SkuImage> skuImageList = skuMapper.getSkuImageList(skuEnter.getSkuNo());
        skuDetailResult.setSkuImageList(skuImageList);

        skuDetailResult.setCode(BusinessCodes.SUCCESS);

        return skuDetailResult;
    }

    @Override
    public Double getMySKUDiscount(String skuNo, Long userId) {
        return skuMapper.getSkuDiscount(skuNo, userId);

    }

    /**
     * SPU下面的所有属性
     * skuList:{1. AttrList,  2. price, 3. inventory}
     *
     * @param skuListEnter
     * @return
     */
    @Override
    public SKUListResult getSKUListFilter(SKUListEnter skuListEnter) {

        String spuCode = skuMapper.getSPUCodeBySKUNo(skuListEnter.getSkuNo());
        // 1. 获取所有库存
        List<SKU> skuList = skuMapper.getAllSKUBySpu(spuCode, SKUStatusEnum.SKU_STATUS_YOUXIAO.getEnName());

        // 2. 获取价格和库存
        for (SKU sku : skuList) {
            List<SkuImage> skuImageList = skuMapper.getSkuImageList(sku.getSkuNo());
            sku.setSkuImageList(skuImageList);

//            List<SkuAttr> skuAttrList = skuMapper.getSkuAttrList(sku.getSkuNo());
//            sku.setSkuAttrList(skuAttrList);
        }
        // 根据spuCode 获取Attr和对应的值

        SKUListResult skuListResult = new SKUListResult();
        skuListResult.setSkuList(skuList);
        skuListResult.setCode(BusinessCodes.SUCCESS);

        return skuListResult;

    }


}
