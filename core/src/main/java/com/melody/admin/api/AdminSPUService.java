package com.melody.admin.api;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface AdminSPUService {

    Long addSPU(SPU spu);


    Page<SPU> querySPUList(Page page);

    int deleteById(long spuId);

    List<SPU> querySPUByBC(String categoryCode, String brandCode);

    SPU  querySPUBySpuCode(String spuCode);

    int updateSPU(SPU spu);


    int deleteAttr(Long attrId, String spuCode);

    int deleteSubSpu(String spuCode, String subSpuCode);

    int deleteSpuDesigner(String spuCode, Long spuDesignerId);
}
