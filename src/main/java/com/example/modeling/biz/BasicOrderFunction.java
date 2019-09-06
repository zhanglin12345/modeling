package com.example.modeling.biz;

import com.example.modeling.dto.BaseOrderDTO;

public interface BasicOrderFunction<T extends BaseOrderDTO> {
    void createOrder(T orderDTO);
    void payOrder(String orderId);
}
