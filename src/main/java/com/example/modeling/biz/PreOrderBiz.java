package com.example.modeling.biz;

import com.example.modeling.dto.PreOrderDTO;
import com.example.modeling.integration.PayService;
import com.example.modeling.model.OrderStateTemplate;
import com.example.modeling.model.OrderStatusEnum;
import com.example.modeling.model.PreOrderBO;
import com.example.modeling.po.PreOrderPO;
import com.example.modeling.repository.PreOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreOrderBiz extends AbstractBasicOrderBiz<PreOrderBO, PreOrderPO, PreOrderDTO> implements OrderPartialPayable {
    private PreOrderRepository preOrderRepository;

    public PreOrderBiz(OrderStateTemplate<PreOrderBO, PreOrderPO, PreOrderDTO> orderStateFactory,
                       PayService payService,
                       PreOrderRepository preOrderRepository) {
        super(orderStateFactory, payService);
        this.preOrderRepository = preOrderRepository;
    }

    @Override
    public void partialPayOrder(String orderId) {
        PreOrderPO orderPO = preOrderRepository.findByOrderId(orderId);
        PreOrderBO orderBO = orderStateFactory.transfer(orderPO);
        orderBO.partialPayOrder(payService);
        persistOrder(orderBO, orderPO.getId());
    }

    @Override
    protected void persistOrder(PreOrderBO preOrderBO, Integer id) {
        PreOrderPO orderPO = orderStateFactory.transfer(preOrderBO);
        orderPO.setId(id);
        preOrderRepository.save(orderPO);
    }

    @Override
    PreOrderPO findOrderPO(String orderId) {
        return preOrderRepository.findByOrderId(orderId);
    }

    @Override
    List<PreOrderPO> findByOrderStatus(OrderStatusEnum orderStatus) {
        return preOrderRepository.findByOrderStatus(orderStatus);
    }

    @Override
    Integer getIdentity(PreOrderPO orderPO) {
        return orderPO.getId();
    }
}
