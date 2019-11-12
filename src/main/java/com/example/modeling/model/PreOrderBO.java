package com.example.modeling.model;

import com.example.modeling.exception.CustomException;
import com.example.modeling.integration.PayService;
import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PreOrderBO extends BaseOrderBO {
    private BigDecimal preMoney;
    private long partialPayTime;
    private long payAllMoneyDelay;

    @Builder
    public PreOrderBO(String orderId, String name, int amount, BigDecimal price, OrderStatusEnum orderStatus, String account,
                      BigDecimal preMoney, long partialPayTime, long payAllMoneyDelay) {
        super(orderId, name, amount, price, orderStatus, account);
        this.preMoney = preMoney;
        this.partialPayTime = partialPayTime;
        this.payAllMoneyDelay = payAllMoneyDelay;
    }

    @Override
    protected BigDecimal getPayOrderMoney() {
        return getTotal().subtract(preMoney);
    }

    public void partialPayOrder(PayService payService) {
        if (OrderStatusEnum.Created.equals(this.orderStatus)) {
            if (payService.pay(account, preMoney)) {
                this.orderStatus = OrderStatusEnum.PartialPaid;
                this.partialPayTime = System.currentTimeMillis();
            } else {
                throw new CustomException("failed to partial pay order " + orderId);
            }
        } else {
            throw new CustomException("cannot pre pay order because the status is incorrect " + orderId);
        }
    }
}
