package com.example.modeling.api;

import com.example.modeling.builder.OrderBuilder;
import com.example.modeling.model.OrderStatusEnum;
import com.example.modeling.po.OrderPO;
import com.example.modeling.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.modeling.builder.OrderBuilder.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(JUnitPlatform.class)
public class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @Autowired
    private OrderRepository orderRepository;

    @AfterEach
    void deleteAll() {
        orderRepository.deleteAll();
    }

    @Test
    void should_create_order() {
        String orderId = "order_11";
        orderController.createOrder(OrderBuilder.getOrder().orderId(orderId).build());
        OrderPO orderPO = orderRepository.findByOrderId(orderId);
        assertEquals(orderId, orderPO.getOrderId());
        assertEquals(ORDER_NAME, orderPO.getName());
        assertEquals(PRICE, orderPO.getPrice());
        assertEquals(TOTAL, orderPO.getTotal());
        assertEquals(AMOUNT, orderPO.getAmount());
        assertEquals(OrderStatusEnum.Created, orderPO.getOrderStatus());
    }

    static ArrayList<Integer> list = new ArrayList<>();

    static void test() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("aa");
        StringBuilder builder = new StringBuilder();
        builder.append("aaa");
    }

    static void lockM(int index) {
        try {
            synchronized (list.get(index)) {
                if (index == 0) {
                    Thread.sleep(500);
                }
                System.out.println("lock1");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
