package com.melody.admin.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.melody.product.dto.Feature;
import com.melody.product.dto.SPU;

import java.util.List;

public interface AdminSPUService {

    int addSPU(SPU spu);


    Page<SPU> querySPUList(Page page);

    int deleteById(long spuId);

    List<SPU> querySPUByBC(String categoryCode, String brandCode);

}
