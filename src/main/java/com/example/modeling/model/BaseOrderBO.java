package com.example.modeling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseOrderBO {
    protected String orderId;
    protected String name;
    protected int amount;
    protected BigDecimal price;
    protected OrderStatusEnum orderStatus;

    BigDecimal getTotal() {
        return price.multiply(BigDecimal.valueOf(amount));
    }
}
