package com.example.modeling.model;


public interface OrderStateFactory<T extends BaseOrderBO, K, G> {
    T create(G orderDTO);
    T transfer(K orderPO);
    K transfer(T orderBO);
}
