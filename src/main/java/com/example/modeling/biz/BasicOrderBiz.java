package com.example.modeling.biz;

import com.example.modeling.dto.BaseOrderDTO;

public interface BasicOrderBiz<typeDTO extends BaseOrderDTO> {
    void createOrder(typeDTO orderDTO);
    void payOrder(String orderId);
    void confirmPayOrder();
}
