package com.example.modeling.biz;

import com.example.modeling.dto.OrderDTO;
import com.example.modeling.integration.PayService;
import com.example.modeling.model.OrderStateFactory;
import com.example.modeling.model.OrderBO;
import com.example.modeling.po.OrderPO;
import com.example.modeling.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements BasicOrderFunction<OrderDTO>, OrderDeliverable {
    private OrderRepository orderRepository;
    private PayService payService;
    private OrderStateFactory<OrderBO, OrderPO, OrderDTO> orderBOFactory;

    public OrderService(OrderRepository orderRepository,
                        PayService payService,
                        OrderStateFactory<OrderBO, OrderPO, OrderDTO> orderBOFactory) {
        this.orderRepository = orderRepository;
        this.payService = payService;
        this.orderBOFactory = orderBOFactory;
    }

    @Override
    public void createOrder(OrderDTO orderDTO) {
        OrderBO orderBO = orderBOFactory.create(orderDTO);
        persistOrder(orderBO, null);
    }

    @Override
    public void payOrder(String orderId) {
        OrderPO orderPO = orderRepository.findByOrderId(orderId);
        OrderBO orderBO =  orderBOFactory.transfer(orderPO);
        orderBO.payOrder(payService);
        persistOrder(orderBO, orderPO.getId());
    }

    @Override
    public void outStockOrder(String orderId) {
        OrderPO orderPO = orderRepository.findByOrderId(orderId);
        OrderBO orderBO =  orderBOFactory.transfer(orderPO);
        orderBO.OutStockOrder();
        persistOrder(orderBO, orderPO.getId());
    }

    @Override
    public void deliverOrder(String orderId) {
        OrderPO orderPO = orderRepository.findByOrderId(orderId);
        OrderBO orderBO =  orderBOFactory.transfer(orderPO);
        orderBO.deliverOrder();
        persistOrder(orderBO, orderPO.getId());
    }

    @Override
    public void deliveredOrder(String orderId) {
        OrderPO orderPO = orderRepository.findByOrderId(orderId);
        OrderBO orderBO =  orderBOFactory.transfer(orderPO);
        orderBO.deliveredOrder();
        persistOrder(orderBO, orderPO.getId());
    }

    private void persistOrder(OrderBO orderBO, Integer id) {
        OrderPO paidOrderPO = orderBOFactory.transfer(orderBO);
        paidOrderPO.setId(id);
        orderRepository.save(paidOrderPO);
    }
}
