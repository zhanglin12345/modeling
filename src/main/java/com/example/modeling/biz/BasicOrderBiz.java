package com.example.modeling.biz;

import com.example.modeling.dto.BaseOrderDTO;

public interface BasicOrderBiz<D extends BaseOrderDTO> {
    void createOrder(D orderDTO);
    void payOrder(String orderId);
    void confirmPayOrder();
}
