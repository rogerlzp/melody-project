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
import com.melody.product.dto.*;
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

            for (SpuComponent spuComponent : spu.getSpuComponentList()) {
                long spuComponentId = baseService.getNextSequence("TR_SPU_COMPONENT");
                spuComponent.setSpuCode(spu.getSpuCode());
                spuComponent.setId(spuComponentId);
                adminSPUMapper.insertSpuComponent(spuComponent);
            }

            // 增加设计师
            for (SpuDesigner spuDesigner : spu.getSpuDesignerList()) {
                long spuDesignerId = baseService.getNextSequence("TR_SPU_DESIGNER");
                spuDesigner.setSpuCode(spu.getSpuCode());
                spuDesigner.setId(spuDesignerId);
                adminSPUMapper.insertSpuDesigner(spuDesigner);
            }

            // 适用空间
            for (SpuSpace spuSpace : spu.getSpuSpaceList()) {
                long spuSpaceId = baseService.getNextSequence("TR_SPU_SPACE");
                adminSPUMapper.insertSpuSpace(spuSpaceId, spu.getSpuCode(), spuSpace.getSpaceId());
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

            List<SpuComponent> spuCompoentList = adminSPUMapper.getSpuComponentBySpuCode(spu.getSpuCode());
            spu.setSpuComponentList(spuCompoentList);

            List<SpuDesigner> spuDesignerList = adminSPUMapper.getSpuDesignerBySpuCode(spu.getSpuCode());
            spu.setSpuDesignerList(spuDesignerList);
            List<SpuSpace> spuSpaceList = adminSPUMapper.getSpuSpaceBySpuCode(spu.getSpuCode());
            spu.setSpuSpaceList(spuSpaceList);
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
                for (SpuAttr spuAttr : spuAttrList) {
                    if (spuAttr.getAttrInputType() == 3) { // 值存储在单独的列里面
                        spuAttr.setAttrValList(adminAttrMapper.queryAttrValList(spuAttr.getAttrId()));
                    }
                }
                spu.setSpuAttrList(spuAttrList);
            }
            List<SpuComponent> spuCompoentList = adminSPUMapper.getSpuComponentBySpuCode(spuCode);
            if (spuCompoentList != null) {
                spu.setSpuComponentList(spuCompoentList);
            }
            List<SpuDesigner> spuDesignerList = adminSPUMapper.getSpuDesignerBySpuCode(spu.getSpuCode());
            if (spuDesignerList != null) {
                spu.setSpuDesignerList(spuDesignerList);
            }
            List<SpuSpace> spuSpaceList = adminSPUMapper.getSpuSpaceBySpuCode(spu.getSpuCode());
            if (spuSpaceList != null) {
                spu.setSpuSpaceList(spuSpaceList);
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
                    if (spuAttr.getId() != null) { // 已经有的，直接更新之
                        adminSPUMapper.updateSpuAttrById(spuAttr.getId(), spuAttr.getAttrId(),
                                spu.getSpuCode(), spuAttr.getSortOrder());
                    } else { // 没有的，新增
                        long spuAttrId = baseService.getNextSequence("TR_SPU_ATTR");
                        spuAttr.setSpuCode(spu.getSpuCode());
                        spuAttr.setId(spuAttrId);
                        adminSPUMapper.insertSpuAttr(spuAttr);
                    }
                }
                for (SpuComponent spuComponent : spu.getSpuComponentList()) {
                    if (spuComponent.getId() != null) { // 已经有的，直接更新之
                        adminSPUMapper.updateSpuComponentById(spuComponent.getId(), spu.getSpuCode(),
                                spuComponent.getSubSpuCode(), spuComponent.getSubNum());
                    } else { // 没有的，新增
                        long spuComponentId = baseService.getNextSequence("TR_SPU_COMPONENT");
                        spuComponent.setSpuCode(spu.getSpuCode());
                        spuComponent.setId(spuComponentId);
                        adminSPUMapper.insertSpuComponent(spuComponent);
                    }
                }
                for (SpuDesigner spuDesigner : spu.getSpuDesignerList()) {
                    if (spuDesigner.getId() != null) { // 已经有的，直接更新之
                        adminSPUMapper.updateSpuDesignerById(spuDesigner.getId(), spu.getSpuCode(),
                                spuDesigner.getDesignerId());
                    } else { // 没有的，新增
                        long spuDesignerId = baseService.getNextSequence("TR_SPU_DESIGNER");
                        spuDesigner.setSpuCode(spu.getSpuCode());
                        spuDesigner.setId(spuDesignerId);
                        adminSPUMapper.insertSpuDesigner(spuDesigner);
                    }
                }

                return updateResult;
            }
        }
        return -1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteAttr(Long attrId, String spuCode) {
        if (attrId != null && spuCode != null) {
            int result = adminSPUMapper.deleteSpuAttr(attrId, spuCode);
            log.info("delete spu attrId result: attrId:" + attrId + " spuCode: " + spuCode +
                    " result: " + result);
            if (result == 1) {
                int attrResult = adminAttrMapper.deleteAttrByAttrId(attrId);
                log.info("delete spu attrId result: attrId:" + attrId +
                        " attrResult: " + attrResult);
                return attrResult;
            }
        }
        return -1;
    }

    @Override
    public int deleteSubSpu(String spuCode, String subSpuCode) {
        int result = adminSPUMapper.deleteSpuComponent(spuCode, subSpuCode);
        if (result == 1) {
            log.info("delete spu attrId result: spuCode:" + spuCode + " subSpuCode: " + subSpuCode +
                    " result: " + result);
            return result;
        }
        return -1;
    }

    @Override
    public int deleteSpuDesigner(String spuCode, Long spuDesignerId) {
        int result = adminSPUMapper.deleteSpuDesigner(spuCode, spuDesignerId);
        if (result == 1) {
            log.info("delete spu designer result: spuCode:" + spuCode + " spuDesignerId: " + spuDesignerId +
                    " result: " + result);
            return result;
        }
        return -1;
    }


}
