package com.example.modeling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class PreOrderDTO extends BaseOrderDTO {

    @JsonProperty("pre_money")
    private BigDecimal preMoney;

    @JsonProperty("pay_all_money_delay")
    private long payAllMoneyDelay;

    @Builder
    public PreOrderDTO(String orderId,String name, int amount, BigDecimal price, BigDecimal preMoney, long payAllMoneyDelay, String account) {
        super(orderId, name, amount, price, account);
        this.preMoney = preMoney;
        this.payAllMoneyDelay = payAllMoneyDelay;
    }
}
