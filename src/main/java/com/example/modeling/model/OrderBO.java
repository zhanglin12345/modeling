package com.example.modeling.model;

import com.example.modeling.integration.PayService;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderBO extends BaseOrderBO {

    @Builder
    public OrderBO(String orderId, String name, int amount, BigDecimal price, OrderStatusEnum orderStatus) {
        super(orderId, name, amount, price, orderStatus);
    }

    public void payOrder(PayService payService) {
        if (OrderStatusEnum.Created.equals(orderStatus)) {
            if (payService.pay(getTotal())) {
                this.orderStatus = OrderStatusEnum.Paid;
            } else {
                throw new RuntimeException("failed to pay order " + orderId);
            }
        } else {
            throw new RuntimeException("tried to pay a order that already paid " + orderId);
        }
    }

    public void OutStockOrder() {
        if (OrderStatusEnum.Paid.equals(orderStatus)) {
            this.orderStatus = OrderStatusEnum.OutStock;
        } else if (OrderStatusEnum.Created.equals(orderStatus)) {
            throw new RuntimeException("tried to out stock a order that not paid " + orderId);
        } else
            throw new RuntimeException("tried to out stock a order that already out " + orderId);
    }


    public void deliverOrder() {
        if (OrderStatusEnum.OutStock.equals(orderStatus)) {
            this.orderStatus = OrderStatusEnum.Delivered;
        } else {
            throw new RuntimeException("tried to deliver a order that not correct status " + orderId);
        }
    }


    public void deliveredOrder() {
        if (OrderStatusEnum.Delivery.equals(orderStatus)) {
            this.orderStatus = OrderStatusEnum.Delivered;
        } else {
            throw new RuntimeException("tried to delivered a order that not correct status " + orderId);
        }
    }
}

