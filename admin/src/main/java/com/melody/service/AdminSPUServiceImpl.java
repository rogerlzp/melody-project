package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminAttrService;
import com.melody.admin.api.AdminSPUService;
import com.melody.common.constant.BusinessCodes;
import com.melody.common.utils.StringUtils;
import com.melody.dao.AdminAttrMapper;
import com.melody.dao.AdminSPUMapper;
import com.melody.exception.BusinessException;
import com.melody.product.dto.Attr;
import com.melody.product.dto.SPU;
import com.melody.product.dto.SkuAttr;
import com.melody.product.dto.SpuAttr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(group = "adminSPUService", timeout = 10000)
public class AdminSPUServiceImpl implements AdminSPUService {

    @Autowired
    AdminSPUMapper adminSPUMapper;

    @Autowired
    AdminAttrMapper adminAttrMapper;

    @Autowired
    BaseServiceImpl baseService;

    private static final Logger log = LoggerFactory.getLogger(AdminSPUServiceImpl.class);


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Long addSPU(SPU spu) {
        long spuId = baseService.getNextSequence("TT_SPU");
        spu.setId(spuId);

        // 创建SPU CODE
        // 先用SPU + ID 来设置
        if (StringUtils.isEmpty(spu.getSpuCode())) {
            String spudCode = "SPU" + spuId;
            spu.setSpuCode(spudCode);
        }


        int insertResult = adminSPUMapper.insert(spu);
        if (insertResult == 1) {
            //获取SpuAttr属性
            for (SpuAttr spuAttr : spu.getSpuAttrList()) {
                long spuAttrId = baseService.getNextSequence("TR_SPU_ATTR");
                spuAttr.setSpuCode(spu.getSpuCode());
                spuAttr.setId(spuAttrId);
                adminSPUMapper.insertSpuAttr(spuAttr);
            }
            return spuId;
        }
        return -1L;
    }

    @Override
    public Page<SPU> querySPUList(Page page) {
        int currentPage = page.getCurrent() - 1; // current为1， 所以往后减一位
        int pageSize = page.getSize();
        int totalCount = adminSPUMapper.countAllSPU(); // 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        List<SPU> records = adminSPUMapper.querySPUList(pager.getOffset(), pageSize);

        // 每个SPU 增加SpuAttr属性
        for (SPU spu : records) {
            List<SpuAttr> spuAttrList = adminSPUMapper.getSpuAttrBySpuCode(spu.getSpuCode());
            spu.setSpuAttrList(spuAttrList);
        }

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
        List<SPU> spuList = adminSPUMapper.querySPUByBC(categoryCode, brandCode);
        return spuList;
    }

    @Override
    public SPU querySPUBySpuCode(String spuCode) {
        SPU spu = adminSPUMapper.querySPUBySpuCode(spuCode);
        if (spu != null) {
            List<SpuAttr> spuAttrList = adminSPUMapper.getSpuAttrBySpuCode(spuCode);
            if (spuAttrList != null) {
                spu.setSpuAttrList(spuAttrList);
            }
        }
        return spu;
    }


    @Override
    public int updateSPU(SPU spu) {
        if (spu.getId() != null) {
            int updateResult = adminSPUMapper.updateSPUByPrimaryKeySelective(spu);
            if (updateResult == 1) {
                //获取SpuAttr属性
                for (SpuAttr spuAttr : spu.getSpuAttrList()) {
                    if (spuAttr.getId()!=null){ // 已经有的，直接更新之
                        adminSPUMapper.updateSpuAttrById(spuAttr.getId(), spuAttr.getAttrId(),
                                spu.getSpuCode(), spuAttr.getSortOrder());
                    } else { // 没有的，新增
                        long spuAttrId = baseService.getNextSequence("TR_SPU_ATTR");
                        spuAttr.setSpuCode(spu.getSpuCode());
                        spuAttr.setId(spuAttrId);
                        adminSPUMapper.insertSpuAttr(spuAttr);
                    }
                    // 已经有的属性，服务器有，但后天没有了，需要删除， 在属性那里来删除

                }
                return updateResult;
            }
        }
        return -1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteAttr(Long attrId, String spuCode) {
        if(attrId !=null && spuCode !=null) {
           int result = adminSPUMapper.deleteSpuAttr(attrId, spuCode);
            log.info("delete spu attrId result: attrId:" + attrId + " spuCode: " + spuCode  +
                    " result: " + result);
           if (result == 1) {
              int attrResult =  adminAttrMapper.deleteAttrByAttrId(attrId);
               log.info("delete spu attrId result: attrId:" + attrId  +
                       " attrResult: " + attrResult);
               return attrResult;
           }
        }

        return -1;
    }

}
