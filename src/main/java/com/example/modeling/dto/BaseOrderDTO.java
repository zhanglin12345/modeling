package com.example.modeling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseOrderDTO {
    private String orderId;
    private String name;
    private int amount;
    private BigDecimal price;
}
