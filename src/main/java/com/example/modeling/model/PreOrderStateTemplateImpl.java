package com.example.modeling.model;

import com.example.modeling.dto.PreOrderDTO;
import com.example.modeling.po.PreOrderPO;
import org.springframework.stereotype.Service;

@Service
public class PreOrderStateTemplateImpl implements OrderStateTemplate<PreOrderBO, PreOrderPO, PreOrderDTO> {

    @Override
    public PreOrderPO transfer(PreOrderBO orderBO) {
        return PreOrderPO.builder()
                .name(orderBO.getName())
                .amount(orderBO.getAmount())
                .price(orderBO.getPrice())
                .orderId(orderBO.getOrderId())
                .orderStatus(orderBO.getOrderStatus())
                .total(orderBO.getTotal())
                .partialPayTime(orderBO.getPartialPayTime())
                .payAllMoneyDelay(orderBO.getPayAllMoneyDelay())
                .preMoney(orderBO.getPreMoney())
                .build();
    }

    @Override
    public PreOrderBO transfer(PreOrderDTO preOrderDTO) {
        return PreOrderBO.builder()
                .orderId(preOrderDTO.getOrderId())
                .name(preOrderDTO.getName())
                .amount(preOrderDTO.getAmount())
                .price(preOrderDTO.getPrice())
                .orderStatus(OrderStatusEnum.Created)
                .preMoney(preOrderDTO.getPreMoney())
                .payAllMoneyDelay(preOrderDTO.getPayAllMoneyDelay())
                .build();
    }

    @Override
    public PreOrderBO transfer(PreOrderPO orderPO) {
        return PreOrderBO.builder()
                .name(orderPO.getName())
                .amount(orderPO.getAmount())
                .orderId(orderPO.getOrderId())
                .orderStatus(orderPO.getOrderStatus())
                .price(orderPO.getPrice())
                .partialPayTime(orderPO.getPartialPayTime())
                .payAllMoneyDelay(orderPO.getPayAllMoneyDelay())
                .preMoney(orderPO.getPreMoney())
                .build();
    }
}
