package com.example.modeling.api;

import com.example.modeling.biz.BasicOrderBiz;
import com.example.modeling.biz.OrderDeliverable;
import com.example.modeling.dto.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderController {

    private BasicOrderBiz<OrderDTO> baseOrderFunction;
    private OrderDeliverable orderDeliverable;

    public OrderController(BasicOrderBiz<OrderDTO> baseOrderFunction, OrderDeliverable orderDeliverable) {
        this.baseOrderFunction = baseOrderFunction;
        this.orderDeliverable = orderDeliverable;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDTO orderDTO) {
        baseOrderFunction.createOrder(orderDTO);
    }

    @PostMapping("/order-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public void payOrder(@PathVariable String orderId) {
        baseOrderFunction.payOrder(orderId);
    }

    @PostMapping("/order-stock")
    @ResponseStatus(HttpStatus.CREATED)
    public void outStockOrder(@PathVariable String orderId) {
        orderDeliverable.outStockOrder(orderId);
    }

    @PostMapping("/order-payment-confirmation")
    @ResponseStatus(HttpStatus.CREATED)
    public void confirmPayOrder() {
        baseOrderFunction.confirmPayOrder();
    }
}
