package com.example.modeling.biz;

import com.example.modeling.dto.BaseOrderDTO;
import com.example.modeling.integration.PayService;
import com.example.modeling.model.BaseOrderBO;
import com.example.modeling.model.OrderStateTemplate;
import com.example.modeling.model.OrderStatusEnum;

import java.util.List;

public abstract class AbstractBasicOrderService<typeBO extends BaseOrderBO, typePO, typeDTO extends BaseOrderDTO> implements BasicOrderBiz<typeDTO> {
    protected final transient OrderStateTemplate<typeBO, typePO, typeDTO> orderStateFactory;
    protected final transient PayService payService;

    AbstractBasicOrderService(OrderStateTemplate<typeBO, typePO, typeDTO> orderStateFactory,
                              PayService payService) {
        this.orderStateFactory = orderStateFactory;
        this.payService = payService;
    }

    @Override
    public void createOrder(typeDTO orderDTO) {
        typeBO orderBO = orderStateFactory.transfer(orderDTO);
        persistOrder(orderBO, null);
    }

    @Override
    public void payOrder(String orderId) {
        typePO orderPO = findOrderPO(orderId);
        typeBO orderBO = orderStateFactory.transfer(orderPO);

        orderBO.payOrder(payService);
        try {
            persistOrder(orderBO, getIdentity(orderPO)); // if fails require cancellation
        } catch (Exception e) {
            orderBO.cancelPayOrder(payService); // if cancellation fails, then payment service will not receive confirm, then payment service need expire and rollback the payment
        }
    }

    @Override
    public void confirmPayOrder() {
        List<typePO> orderPOs = findByOrderStatus(OrderStatusEnum.Paid_not_Confirm);
        orderPOs.forEach(orderPO -> {
            typeBO orderBO = orderStateFactory.transfer(orderPO);
            orderBO.confirmPayOrder(payService); //un-confirmed payment will be rollback after expire time
            persistOrder(orderBO, getIdentity(orderPO)); //todo should be batch persist
        });
    }

    abstract void persistOrder(typeBO preOrderBO, Integer id);
    abstract typePO findOrderPO(String orderId);
    abstract List<typePO> findByOrderStatus(OrderStatusEnum orderStatus);
    abstract Integer getIdentity(typePO orderPO);
}
