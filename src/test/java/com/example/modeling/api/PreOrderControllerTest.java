package com.example.modeling.api;

import com.example.modeling.builder.OrderBuilder;
import com.example.modeling.model.OrderStatusEnum;
import com.example.modeling.po.PreOrderPO;
import com.example.modeling.repository.PreOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.modeling.builder.OrderBuilder.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(JUnitPlatform.class)
class PreOrderControllerTest {

    @Autowired
    private PreOrderController preOrderController;

    @Autowired
    private PreOrderRepository preOrderRepository;

    @BeforeEach
    void deleteAll() {
        preOrderRepository.deleteAll();
    }

    @Test
    void should_save_pre_order() {
        String orderId = "order_1";
        preOrderController.createOrder(OrderBuilder.getPreOrder().orderId(orderId).build());
        PreOrderPO orderPO = preOrderRepository.findByOrderId(orderId);
        assertEquals(orderId, orderPO.getOrderId());
        assertEquals(ORDER_NAME, orderPO.getName());
        assertEquals(PRICE, orderPO.getPrice());
        assertEquals(TOTAL, orderPO.getTotal());
        assertEquals(AMOUNT, orderPO.getAmount());
        assertEquals(OrderStatusEnum.Created, orderPO.getOrderStatus());
        assertEquals(PRE_MONEY, orderPO.getPreMoney());
        assertEquals(PAY_ALL_MONEY_DELAY, orderPO.getPayAllMoneyDelay());
    }

    @Test
    void should_partial_pay_order() {
        String orderId = "order_2";
        preOrderController.createOrder(OrderBuilder.getPreOrder().orderId(orderId).build());
        preOrderController.partialPayOrder(orderId);
        PreOrderPO orderPO = preOrderRepository.findByOrderId(orderId);
        assertEquals(orderId, orderPO.getOrderId());
        assertEquals(ORDER_NAME, orderPO.getName());
        assertEquals(PRICE, orderPO.getPrice());
        assertEquals(TOTAL, orderPO.getTotal());
        assertEquals(AMOUNT, orderPO.getAmount());
        assertEquals(OrderStatusEnum.PartialPaid, orderPO.getOrderStatus());
        assertEquals(PRE_MONEY, orderPO.getPreMoney());
        assertEquals(PAY_ALL_MONEY_DELAY, orderPO.getPayAllMoneyDelay());
        assertTrue(orderPO.getPartialPayTime() > 0);
    }
}
