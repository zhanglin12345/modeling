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

    private transient final BasicOrderBiz<OrderDTO> basicOrderBiz;
    private transient final OrderDeliverable orderDeliverable;

    public OrderController(BasicOrderBiz<OrderDTO> basicOrderBiz, OrderDeliverable orderDeliverable) {
        this.basicOrderBiz = basicOrderBiz;
        this.orderDeliverable = orderDeliverable;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDTO orderDTO) {
        basicOrderBiz.createOrder(orderDTO);
    }

    @PostMapping("/order-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public void payOrder(@PathVariable String orderId) {
        basicOrderBiz.payOrder(orderId);
    }

    @PostMapping("/order-stock")
    @ResponseStatus(HttpStatus.CREATED)
    public void outStockOrder(@PathVariable String orderId) {
        orderDeliverable.outStockOrder(orderId);
    }

    @PostMapping("/order-payment-confirmation")
    @ResponseStatus(HttpStatus.CREATED)
    public void confirmPayOrder() {
        basicOrderBiz.confirmPayOrder();
    }
}
