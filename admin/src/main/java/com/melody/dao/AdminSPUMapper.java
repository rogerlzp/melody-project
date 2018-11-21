package com.melody.dao;

import com.melody.product.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminSPUMapper {

    int insert(SPU spu);

    List<SPU> querySPUList(@Param(value = "offset") Integer offset,
                           @Param(value = "pageSize") Integer pageSize);

    int countAllSPU();

    int countSpuSpace( @Param(value = "spuCode") String spuCode,
                       @Param(value = "spaceId") Integer spaceId);

    int deleteBySPUId(@Param(value = "id") Long id);

    List<SPU> querySPUByBC(@Param(value = "categoryCode") String categoryCode,
                           @Param(value = "brandCode") String brandCode);

    int insertSpuAttr(SpuAttr spuAttr);
    int insertSpuSpace(@Param(value = "id") Long id, @Param(value = "spuCode") String spuCode,
                       @Param(value = "spaceId") Integer spaceId);

    List<SpuSpace> getSpuSpaceBySpuCode(@Param(value = "spuCode") String spuCode);



    int insertSpuComponent(SpuComponent spuComponent);

    int insertSpuDesigner(SpuDesigner spuDesigner);

    List<SpuAttr> getSpuAttrBySpuCode(@Param(value = "spuCode") String spuCode);

    List<SpuComponent> getSpuComponentBySpuCode(@Param(value = "spuCode") String spuCode);

    List<SpuDesigner> getSpuDesignerBySpuCode(@Param(value = "spuCode") String spuCode);


    SPU querySPUBySpuCode(@Param(value = "spuCode") String spuCode);

    int updateSPUByPrimaryKeySelective(SPU spu);

    int updateSpuAttrById(@Param(value = "id") Long id,
                          @Param(value = "attrId") Long attrId,
                          @Param(value = "spuCode") String spuCode,
                          @Param(value = "sortOrder") Integer sortOrder);

    int deleteSpuAttr(@Param(value = "attrId") Long attrId,
                      @Param(value = "spuCode") String spuCode);

    int updateSpuComponentById(@Param(value = "id") Long id,
                               @Param(value = "spuCode") String spuCode,
                               @Param(value = "subSpuCode") String subSpuCode,
                               @Param(value = "subNum") Integer subNum);

    int updateSpuDesignerById(@Param(value = "id") Long id,
                               @Param(value = "spuCode") String spuCode,
                               @Param(value = "designerId") Long designerId);

    int deleteSpuComponent(
            @Param(value = "spuCode") String spuCode,
            @Param(value = "subSpuCode") String subSpuCode
    );
    int deleteSpuDesigner(
            @Param(value = "spuCode") String spuCode,
            @Param(value = "designerId") Long designerId);

    int deleteSpuComponent(
            @Param(value = "spuCode") String spuCode);
}
