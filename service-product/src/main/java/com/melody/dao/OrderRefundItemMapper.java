package com.melody.dao;

import com.melody.generated.model.OrderRefundItem;

public interface OrderRefundItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderRefundItem record);

    int insertSelective(OrderRefundItem record);

    OrderRefundItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderRefundItem record);

    int updateByPrimaryKey(OrderRefundItem record);
}