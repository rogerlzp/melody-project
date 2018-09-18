package com.melody.dao;


import com.melody.product.dto.Order;
import com.melody.product.dto.OrderDetailResult;
import com.melody.product.dto.OrderExpress;
import com.melody.product.dto.OrderItem;
import com.melody.user.dto.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    int insertOrder(Order record);

    int insertOrderItem(OrderItem orderItem);

    int insertOrderExpress(OrderExpress orderExpress);

    int insertSelective(Order record);


    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    OrderDetailResult getOrderDetailByOrderNo(@Param(value = "orderNo") String orderNo);


    UserAddress selectByAddressId(Long id);

    int resetDefaultAddress(@Param(value = "userId") Long userId);

    List<UserAddress> getUserAddress(@Param(value = "openId") String openId);

    UserAddress getDefaultAddressByUserId(@Param(value = "userId") Long userId);

    int updateOrderStatus(@Param(value = "orderNo") String orderNo, @Param(value = "status") String status);

    int countAllMyOrder(@Param(value = "userId") Long userId, @Param(value = "status") String status);

    List<Order> queryOrderList(@Param(value = "userId") Long userId, @Param(value = "status") String status,
                               @Param(value = "offset") Integer offset,
                               @Param(value = "pageSize") Integer pageSize);

}