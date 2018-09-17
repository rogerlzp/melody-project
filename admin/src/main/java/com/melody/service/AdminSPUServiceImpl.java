package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminFeatureService;
import com.melody.admin.api.AdminSPUService;
import com.melody.common.constant.BusinessCodes;
import com.melody.common.utils.StringUtils;
import com.melody.dao.AdminFeatureMapper;
import com.melody.dao.AdminSPUMapper;
import com.melody.exception.BusinessException;
import com.melody.product.dto.Feature;
import com.melody.product.dto.SPU;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(group = "adminSPUService", timeout = 10000)
public class AdminSPUServiceImpl implements AdminSPUService {

    @Autowired
    AdminSPUMapper adminSPUMapper;

    @Autowired
    BaseServiceImpl baseService;

    @Override
    public int addSPU(SPU spu) {
        long spuId = baseService.getNextSequence("TT_FEATURE");
        spu.setId(spuId);

        // 创建SPU CODE
        // 先用SPU + ID 来设置
        String spudCode = "SPU" + spuId;
        spu.setSpuCode(spudCode);

        int insertResult = adminSPUMapper.insert(spu);
        return insertResult;
    }

    @Override
    public Page<SPU> querySPUList(Page page) {
        int currentPage = page.getCurrent() - 1; // current为1， 所以往后减一位
        int pageSize = page.getSize();
        int totalCount = adminSPUMapper.countAllSPU(); // 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        List<SPU> records = adminSPUMapper.querySPUList(pager.getOffset(), pageSize);

        Page<SPU> brandPage = new Page<SPU>();
        brandPage.setRecords(records);
        brandPage.setSize(pageSize);
        brandPage.setCurrent(currentPage);
        brandPage.setTotal(totalCount);
        return brandPage;
    }

    @Override
    public int deleteById(long spuId) {
        int deleteResult = adminSPUMapper.deleteBySPUId(spuId);
        return deleteResult;
    }

    @Override
    public List<SPU> querySPUByBC(String categoryCode, String brandCode) {
//        if (StringUtils.isEmpty(categoryCode)) {
//            throw BusinessException(BusinessCodes.ERROR, "categoryCode is empet");
//        }
//        if StringUtils.isEmpty(categoryCode) {
//
//        }

        List<SPU> spuList = adminSPUMapper.querySPUByBC(categoryCode, brandCode);
        return spuList;
    }

}
