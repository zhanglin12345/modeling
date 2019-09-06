package com.example.modeling.model;

import com.example.modeling.integration.PayService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

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
    public PreOrderBO(String orderId, String name, int amount, BigDecimal price, OrderStatusEnum orderStatus,
                      BigDecimal preMoney, long partialPayTime, long payAllMoneyDelay) {
        super(orderId, name, amount, price, orderStatus);
        this.preMoney = preMoney;
        this.partialPayTime = partialPayTime;
        this.payAllMoneyDelay = payAllMoneyDelay;
    }

    public void payOrder(PayService payService) {
        if (System.currentTimeMillis() > payAllMoneyDelay + partialPayTime) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "time is up!");
        }

        if (OrderStatusEnum.PartialPaid.equals(this.orderStatus)) {
            if (payService.pay(preMoney)) {
                this.orderStatus = OrderStatusEnum.Paid;
            } else {
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "failed to pay order " + orderId);
            }
        } else {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "cannot pre pay order because the status is incorrect " + orderId);
        }
    }

    public void partialPayOrder(PayService payService) {
        if (OrderStatusEnum.Created.equals(this.orderStatus)) {
            if (payService.pay(preMoney)) {
                this.orderStatus = OrderStatusEnum.PartialPaid;
                this.partialPayTime = System.currentTimeMillis();
            } else {
                throw new RuntimeException("failed to partial pay order " + orderId);
            }
        } else {
            throw new RuntimeException("cannot pre pay order because the status is incorrect " + orderId);
        }
    }
}
