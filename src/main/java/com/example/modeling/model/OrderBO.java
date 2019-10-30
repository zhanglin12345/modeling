package com.example.modeling.model;

import com.example.modeling.exception.CustomException;
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
    public OrderBO(String orderId, String name, int amount, BigDecimal price, OrderStatusEnum orderStatus, String account) {
        super(orderId, name, amount, price, orderStatus, account);
    }

    @Override
    protected BigDecimal getPayOrderMoney() {
        return getTotal();
    }

    public void outStockOrder() {
        if (OrderStatusEnum.Paid.equals(orderStatus)) {
            this.orderStatus = OrderStatusEnum.OutStock;
        } else if (OrderStatusEnum.Created.equals(orderStatus)) {
            throw new CustomException("tried to out stock a order that not paid " + orderId);
        } else
            throw new CustomException("tried to out stock a order that already out " + orderId);
    }


    public void deliverOrder() {
        if (OrderStatusEnum.OutStock.equals(orderStatus)) {
            this.orderStatus = OrderStatusEnum.Delivered;
        } else {
            throw new CustomException("tried to deliver a order that not correct status " + orderId);
        }
    }


    public void deliveredOrder() {
        if (OrderStatusEnum.Delivery.equals(orderStatus)) {
            this.orderStatus = OrderStatusEnum.Delivered;
        } else {
            throw new CustomException("tried to delivered a order that not correct status " + orderId);
        }
    }
}

