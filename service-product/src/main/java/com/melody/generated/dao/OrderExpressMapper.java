package com.melody.generated.dao;

import com.melody.generated.model.OrderExpress;

public interface OrderExpressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderExpress record);

    int insertSelective(OrderExpress record);

    OrderExpress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderExpress record);

    int updateByPrimaryKey(OrderExpress record);
}