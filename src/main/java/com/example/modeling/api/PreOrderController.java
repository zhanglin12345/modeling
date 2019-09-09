package com.example.modeling.api;

import com.example.modeling.biz.BasicOrderBiz;
import com.example.modeling.biz.OrderPartialPayable;
import com.example.modeling.dto.PreOrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pre-order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PreOrderController {
    private BasicOrderBiz<PreOrderDTO> baseOrderFunction;
    private OrderPartialPayable orderPartialPayable;

    public PreOrderController(BasicOrderBiz<PreOrderDTO> baseOrderFunction, OrderPartialPayable orderPartialPayable) {
        this.baseOrderFunction = baseOrderFunction;
        this.orderPartialPayable = orderPartialPayable;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody PreOrderDTO orderDTO) {
        baseOrderFunction.createOrder(orderDTO);
    }

    @PostMapping("/partial-order-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public void partialPayOrder(@PathVariable String orderId) {
        orderPartialPayable.partialPayOrder(orderId);
    }

    @PostMapping("/order-payment-confirmation")
    @ResponseStatus(HttpStatus.CREATED)
    public void confirmPayOrder() {
        baseOrderFunction.confirmPayOrder();
    }

}
