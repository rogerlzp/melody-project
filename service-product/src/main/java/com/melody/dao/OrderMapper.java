package com.melody.dao;

import com.melody.generated.model.Order;
import com.melody.generated.model.OrderKey;

public interface OrderMapper {
    int deleteByPrimaryKey(OrderKey key);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(OrderKey key);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}