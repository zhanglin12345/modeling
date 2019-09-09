package com.example.modeling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseOrderDTO {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("account")
    private String account;
}
