package com.example.modeling.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends BaseOrderDTO {
    @Builder
    public OrderDTO(String orderId,String name, Integer amount, BigDecimal price, String account) {
        super(orderId, name, amount, price, account);
    }
}
