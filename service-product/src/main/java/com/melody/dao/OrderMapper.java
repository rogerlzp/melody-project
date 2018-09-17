package com.melody.dao;


import com.melody.product.dto.Order;
import com.melody.product.dto.OrderExpress;
import com.melody.product.dto.OrderItem;

public interface OrderMapper {

    int insertOrder(Order record);
    int insertOrderItem(OrderItem orderItem);
    int insertOrderExpress(OrderExpress orderExpress);

    int insertSelective(Order record);


    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}