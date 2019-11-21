package com.example.modeling.api;

import com.example.modeling.model.OrderStatusEnum;
import com.example.modeling.po.PreOrderPO;
import com.example.modeling.repository.PreOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.example.modeling.builder.OrderBuilderUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(JUnitPlatform.class)
@ActiveProfiles("test")
class PreOrderControllerTest {

    @Autowired
    private transient PreOrderController preOrderController;

    @Autowired
    private transient PreOrderRepository preOrderRepository;

    @BeforeEach
    void deleteAll() {
        preOrderRepository.deleteAll();
    }

    @Test
    void should_save_pre_order() {
        String orderId = "order_1";
        preOrderController.createOrder(getPreOrder().orderId(orderId).build());
        PreOrderPO orderPO = preOrderRepository.findByOrderId(orderId);
        assertEquals(orderId, orderPO.getOrderId());
        assertEquals(ORDER_NAME, orderPO.getName());
        assertEquals(PRICE, orderPO.getPrice());
        assertEquals(TOTAL, orderPO.getTotal());
        assertEquals(AMOUNT, orderPO.getAmount());
        assertEquals(OrderStatusEnum.CREATED, orderPO.getOrderStatus());
        assertEquals(PRE_MONEY, orderPO.getPreMoney());
        assertEquals(PAY_ALL_MONEY_DELAY, orderPO.getPayAllMoneyDelay());
    }

    @Test
    void should_partial_pay_order() {
        String orderId = "order_2";
        preOrderController.createOrder(getPreOrder().orderId(orderId).build());
        preOrderController.partialPayOrder(orderId);
        PreOrderPO orderPO = preOrderRepository.findByOrderId(orderId);
        assertEquals(orderId, orderPO.getOrderId());
        assertEquals(ORDER_NAME, orderPO.getName());
        assertEquals(PRICE, orderPO.getPrice());
        assertEquals(TOTAL, orderPO.getTotal());
        assertEquals(AMOUNT, orderPO.getAmount());
        assertEquals(OrderStatusEnum.PARTIAL_PAID, orderPO.getOrderStatus());
        assertEquals(PRE_MONEY, orderPO.getPreMoney());
        assertEquals(PAY_ALL_MONEY_DELAY, orderPO.getPayAllMoneyDelay());
        assertTrue(orderPO.getPartialPayTime() > 0);
    }
}
