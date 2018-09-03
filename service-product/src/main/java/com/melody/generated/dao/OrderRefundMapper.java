package com.melody.generated.dao;

import com.melody.generated.model.OrderRefund;

public interface OrderRefundMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderRefund record);

    int insertSelective(OrderRefund record);

    OrderRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderRefund record);

    int updateByPrimaryKey(OrderRefund record);
}