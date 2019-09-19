package com.example.modeling.model;

import com.example.modeling.dto.OrderDTO;
import com.example.modeling.po.OrderPO;
import org.springframework.stereotype.Service;

@Service
public class OrderStateTemplateImpl implements OrderStateTemplate<OrderBO, OrderPO, OrderDTO> {

    @Override
    public OrderBO transfer(OrderDTO orderDTO) {
        return OrderBO.builder()
                .orderId(orderDTO.getOrderId())
                .name(orderDTO.getName())
                .amount(orderDTO.getAmount())
                .price(orderDTO.getPrice())
                .orderStatus(OrderStatusEnum.Created)
                .build();
    }

    @Override
    public OrderBO transfer(OrderPO orderPO) {
        return OrderBO.builder()
                .name(orderPO.getName())
                .amount(orderPO.getAmount())
                .orderId(orderPO.getOrderId())
                .orderStatus(orderPO.getOrderStatus())
                .price(orderPO.getPrice())
                .build();
    }

    @Override
    public OrderPO transfer(OrderBO orderBO) {
        return OrderPO.builder()
                .name(orderBO.getName())
                .amount(orderBO.getAmount())
                .price(orderBO.getPrice())
                .orderId(orderBO.getOrderId())
                .orderStatus(orderBO.getOrderStatus())
                .total(orderBO.getTotal())
                .build();
    }
}
