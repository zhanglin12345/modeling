package com.example.modeling.model;


import com.example.modeling.dto.BaseOrderDTO;

public interface OrderStateTemplate<T extends BaseOrderBO, K, G extends BaseOrderDTO> {
    T transfer(G orderDTO);
    T transfer(K orderPO);
    K transfer(T orderBO);
}
