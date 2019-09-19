package com.example.modeling.biz;

import com.example.modeling.dto.OrderDTO;
import com.example.modeling.integration.PayService;
import com.example.modeling.model.OrderBO;
import com.example.modeling.model.OrderStateTemplate;
import com.example.modeling.model.OrderStatusEnum;
import com.example.modeling.po.OrderPO;
import com.example.modeling.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderBiz extends AbstractBasicOrderBiz<OrderBO, OrderPO, OrderDTO> implements OrderDeliverable {
    private OrderRepository orderRepository;

    public OrderBiz(OrderStateTemplate<OrderBO, OrderPO, OrderDTO> orderStateFactory,
                    PayService payService,
                    OrderRepository orderRepository) {
        super(orderStateFactory, payService);
        this.orderRepository = orderRepository;
    }

    @Override
    public void outStockOrder(String orderId) {
        OrderPO orderPO = findOrderPO(orderId);
        OrderBO orderBO =  orderStateFactory.transfer(orderPO);
        orderBO.OutStockOrder();
        persistOrder(orderBO, orderPO.getId());
    }

    @Override
    public void deliverOrder(String orderId) {
        OrderPO orderPO = findOrderPO(orderId);
        OrderBO orderBO =  orderStateFactory.transfer(orderPO);
        orderBO.deliverOrder();
        persistOrder(orderBO, orderPO.getId());
    }

    @Override
    public void deliveredOrder(String orderId) {
        OrderPO orderPO = findOrderPO(orderId);
        OrderBO orderBO =  orderStateFactory.transfer(orderPO);
        orderBO.deliveredOrder();
        persistOrder(orderBO, orderPO.getId());
    }

    @Override
    protected void persistOrder(OrderBO orderBO, Integer id) {
        OrderPO paidOrderPO = orderStateFactory.transfer(orderBO);
        paidOrderPO.setId(id);
        orderRepository.save(paidOrderPO);
    }

    @Override
    OrderPO findOrderPO(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    List<OrderPO> findByOrderStatus(OrderStatusEnum orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }

    @Override
    Integer getIdentity(OrderPO orderPO) {
        return orderPO.getId();
    }
}
