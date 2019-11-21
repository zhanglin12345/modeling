package com.example.modeling.model;

import com.example.modeling.exception.CustomException;
import com.example.modeling.integration.PayService;
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
    protected Integer amount;
    protected BigDecimal price;
    protected OrderStatusEnum orderStatus;
    protected String account;

    BigDecimal getTotal() {
        return price.multiply(BigDecimal.valueOf(amount));
    }

    public void payOrder(PayService payService) {
        if (!OrderStatusEnum.CREATED.equals(orderStatus)) {
            throw new CustomException("tried to pay a order that already paid " + orderId);
        }
        this.orderStatus = OrderStatusEnum.PAID_NOT_CONFIRM;
        lockMoney(payService, getPayOrderMoney());

        try {
            payService.pay(account, getPayOrderMoney());
        } catch (Exception e) {
            cancelPayOrder(payService); // if cancellation fails the state will be back to normal once expired
        }
    }

    public void cancelPayOrder(PayService payService) {
        this.orderStatus = OrderStatusEnum.CREATED;
        unLockMoney(payService, getPayOrderMoney());
    }

    public void confirmPayOrder(PayService payService) {
        if (!OrderStatusEnum.PAID_NOT_CONFIRM.equals(orderStatus)) {
            throw new CustomException("only paid not confirmed order can be confirmed " + orderId);
        }

        if (payService.confirmMoneyPaid(account, getPayOrderMoney())) {
            orderStatus = OrderStatusEnum.PAID;
        } else {
            orderStatus = OrderStatusEnum.PARTIAL_PAID;
        }
    }

    protected abstract BigDecimal getPayOrderMoney();

    private void lockMoney(PayService payService, BigDecimal value) {
        final long expiredDuration = 5000;
        if (!payService.lockMoney(account, value, expiredDuration)) {
            throw new CustomException(String.format("failed to lock order %s,", orderId));
        }
    }

    private void unLockMoney(PayService payService, BigDecimal value) {
        if (!payService.unLockMoney(account, value)) {
            throw new CustomException(String.format("failed to unlock order %s,", orderId));
        }
    }
}
