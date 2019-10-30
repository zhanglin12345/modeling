package com.example.modeling.builder;

import com.example.modeling.dto.OrderDTO;
import com.example.modeling.dto.PreOrderDTO;

import java.math.BigDecimal;

public class OrderBuilder {
    private final static String ORDER_ID = "testId";
    public final static String ORDER_NAME = "testName";
    public final static BigDecimal PRICE = BigDecimal.valueOf(9.99);
    public final static BigDecimal PRE_MONEY = BigDecimal.valueOf(5);
    public final static long PAY_ALL_MONEY_DELAY = 500000000;
    public final static BigDecimal TOTAL = BigDecimal.valueOf(9.99).multiply(BigDecimal.valueOf(2));
    public final static Integer AMOUNT = 2;

    public static OrderDTO.OrderDTOBuilder getOrder() {
        return OrderDTO.builder()
                .orderId(ORDER_ID)
                .name(ORDER_NAME)
                .amount(AMOUNT)
                .price(PRICE);
    }

    public static PreOrderDTO.PreOrderDTOBuilder getPreOrder() {
        return PreOrderDTO.builder()
                .orderId(ORDER_ID)
                .name(ORDER_NAME)
                .amount(AMOUNT)
                .price(PRICE)
                .preMoney(PRE_MONEY)
                .payAllMoneyDelay(PAY_ALL_MONEY_DELAY);
    }
}
