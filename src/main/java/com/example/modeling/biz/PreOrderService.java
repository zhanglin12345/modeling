package com.example.modeling.biz;

import com.example.modeling.dto.PreOrderDTO;
import com.example.modeling.integration.PayService;
import com.example.modeling.model.BaseOrderBOFactory;
import com.example.modeling.model.PreOrderBO;
import com.example.modeling.po.PreOrderPO;
import com.example.modeling.repository.PreOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class PreOrderService implements BasicOrderFunction<PreOrderDTO>, OrderPartialPayable {
    private PreOrderRepository preOrderRepository;
    private PayService payService;
    private BaseOrderBOFactory<PreOrderBO, PreOrderPO, PreOrderDTO> orderBOFactory;

    public PreOrderService(PreOrderRepository preOrderRepository,
                           PayService payService,
                           BaseOrderBOFactory<PreOrderBO, PreOrderPO, PreOrderDTO> orderBOFactory) {
        this.preOrderRepository = preOrderRepository;
        this.payService = payService;
        this.orderBOFactory = orderBOFactory;
    }

    @Override
    public void createOrder(PreOrderDTO orderDTO) {
        PreOrderBO orderBO = orderBOFactory.create(orderDTO);
        persistPreOrder(orderBO, null);
    }

    @Override
    public void payOrder(String orderId) {
        PreOrderPO orderPO = preOrderRepository.findByOrderId(orderId);
        PreOrderBO orderBO = orderBOFactory.transfer(orderPO);
        orderBO.payOrder(payService);
        persistPreOrder(orderBO, orderPO.getId());
    }

    @Override
    public void partialPayOrder(String orderId) {
        PreOrderPO orderPO = preOrderRepository.findByOrderId(orderId);
        PreOrderBO orderBO = orderBOFactory.transfer(orderPO);
        orderBO.partialPayOrder(payService);
        persistPreOrder(orderBO, orderPO.getId());
    }

    private void persistPreOrder(PreOrderBO preOrderBO, Integer id) {
        PreOrderPO orderPO = orderBOFactory.transfer(preOrderBO);
        orderPO.setId(id);
        preOrderRepository.save(orderPO);
    }
}
