package com.melody.dao;

import com.melody.product.dto.OrderRefund;
import com.melody.product.dto.OrderRefundItem;

public interface OrderRefundMapper {

    int deleteByPrimaryKey(Long id);

    int insert(OrderRefund record);

    int insertSelective(OrderRefund record);

    OrderRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderRefund record);

    int updateByPrimaryKey(OrderRefund record);


    // 插入OrderRefundItem
    int insertOrderRefundItem(OrderRefundItem orderRefundItem);

}
