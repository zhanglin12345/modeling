package com.example.modeling.biz;

import com.example.modeling.dto.BaseOrderDTO;
import com.example.modeling.integration.PayService;
import com.example.modeling.model.BaseOrderBO;
import com.example.modeling.model.OrderStateTemplate;
import com.example.modeling.model.OrderStatusEnum;

import java.util.List;

public abstract class AbstractBasicOrderService<B extends BaseOrderBO, P, D extends BaseOrderDTO> implements BasicOrderBiz<D> {
    protected final transient OrderStateTemplate<B, P, D> orderStateFactory;
    protected final transient PayService payService;

    AbstractBasicOrderService(OrderStateTemplate<B, P, D> orderStateFactory,
                              PayService payService) {
        this.orderStateFactory = orderStateFactory;
        this.payService = payService;
    }

    @Override
    public void createOrder(D orderDTO) {
        B orderBO = orderStateFactory.transfer(orderDTO);
        persistOrder(orderBO, null);
    }

    @Override
    public void payOrder(String orderId) {
        P orderPO = findOrderPO(orderId);
        B orderBO = orderStateFactory.transfer(orderPO);

        orderBO.payOrder(payService);
        try {
            persistOrder(orderBO, getIdentity(orderPO)); // if fails require cancellation
        } catch (Exception e) {
            // if cancellation fails, then payment service will not receive confirm,
            // then payment service need expire and rollback the payment
            orderBO.cancelPayOrder(payService);
        }
    }

    @Override
    public void confirmPayOrder() {
        List<P> orderPOs = findByOrderStatus(OrderStatusEnum.PAID_NOT_CONFIRM);
        orderPOs.forEach(orderPO -> {
            B orderBO = orderStateFactory.transfer(orderPO);
            orderBO.confirmPayOrder(payService); //un-confirmed payment will be rollback after expire time
            persistOrder(orderBO, getIdentity(orderPO)); //todo should be batch persist
        });
    }

    abstract void persistOrder(B preOrderBO, Integer id);
    abstract P findOrderPO(String orderId);
    abstract List<P> findByOrderStatus(OrderStatusEnum orderStatus);
    abstract Integer getIdentity(P orderPO);
}
