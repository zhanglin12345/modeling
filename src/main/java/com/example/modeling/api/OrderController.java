package com.example.modeling.api;

import com.example.modeling.biz.BasicOrderFunction;
import com.example.modeling.biz.OrderDeliverable;
import com.example.modeling.dto.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private BasicOrderFunction<OrderDTO> baseOrderFunction;
    private OrderDeliverable orderDeliverable;

    public OrderController(BasicOrderFunction<OrderDTO> baseOrderFunction, OrderDeliverable orderDeliverable) {
        this.baseOrderFunction = baseOrderFunction;
        this.orderDeliverable = orderDeliverable;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(OrderDTO orderDTO) {
        baseOrderFunction.createOrder(orderDTO);
    }

    @PostMapping("/order-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public void payOrder(String orderId) {
        baseOrderFunction.payOrder(orderId);
    }

    @PostMapping("/order-stock")
    @ResponseStatus(HttpStatus.CREATED)
    public void outStockOrder(String orderId) {
        orderDeliverable.outStockOrder(orderId);
    }
}
