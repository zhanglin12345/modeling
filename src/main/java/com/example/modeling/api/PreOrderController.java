package com.example.modeling.api;

import com.example.modeling.biz.BasicOrderBiz;
import com.example.modeling.biz.OrderPartialPayable;
import com.example.modeling.dto.PreOrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pre-order")
public class PreOrderController {
    private BasicOrderBiz<PreOrderDTO> baseOrderFunction;
    private OrderPartialPayable orderPartialPayable;

    public PreOrderController(BasicOrderBiz<PreOrderDTO> baseOrderFunction, OrderPartialPayable orderPartialPayable) {
        this.baseOrderFunction = baseOrderFunction;
        this.orderPartialPayable = orderPartialPayable;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(PreOrderDTO orderDTO) {
        baseOrderFunction.createOrder(orderDTO);
    }

    @PostMapping("/partial-order-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public void partialPayOrder(String orderId) {
        orderPartialPayable.partialPayOrder(orderId);
    }

    @PostMapping("/order-payment-confirmation")
    @ResponseStatus(HttpStatus.CREATED)
    public void confirmPayOrder() {
        baseOrderFunction.confirmPayOrder();
    }

}
