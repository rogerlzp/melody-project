package com.melody.dao;


import com.melody.product.dto.Attr;
import com.melody.product.dto.AttrOption;
import com.melody.product.dto.SPU;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SPUMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SPU record);

    int insertSelective(SPU record);

    SPU selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SPU record);

    int updateByPrimaryKey(SPU record);

    SPU selectBySpuCode(String spuCode);

    List<Attr> getAttrListBySpuCode(@Param(value = "spuCode") String spuCode);

    List<AttrOption> getAttrOptionByFeationId(@Param(value = "AttrId") Integer AttrId);
}