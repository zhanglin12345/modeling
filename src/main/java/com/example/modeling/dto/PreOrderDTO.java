package com.example.modeling.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PreOrderDTO extends BaseOrderDTO {
    private BigDecimal preMoney;
    private long payAllMoneyDelay;

    @Builder
    public PreOrderDTO(String orderId,String name, int amount, BigDecimal price, BigDecimal preMoney, long payAllMoneyDelay) {
        super(orderId, name, amount, price);
        this.preMoney = preMoney;
        this.payAllMoneyDelay = payAllMoneyDelay;
    }
}
