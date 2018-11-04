package com.melody.dao;

import com.melody.product.dto.Category;
import com.melody.product.dto.SPU;
import com.melody.product.dto.SpuAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminSPUMapper {

    int insert(SPU spu);

    List<SPU> querySPUList(@Param(value = "offset") Integer offset,
                                     @Param(value = "pageSize") Integer pageSize);

    int countAllSPU();

    int deleteBySPUId(@Param(value = "id") Long id);

    List<SPU>  querySPUByBC(@Param(value = "categoryCode") String categoryCode,
                            @Param(value = "brandCode") String brandCode);

    int insertSpuAttr(SpuAttr spuAttr);

}
