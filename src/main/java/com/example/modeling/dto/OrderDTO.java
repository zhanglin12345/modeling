package com.example.modeling.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends BaseOrderDTO {
    @Builder
    public OrderDTO(String orderId,String name, int amount, BigDecimal price) {
        super(orderId, name, amount, price);
    }
}
