package com.example.modeling.repository;

import com.example.modeling.model.OrderStatusEnum;
import com.example.modeling.po.OrderPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderPO, Integer> {
    OrderPO findByOrderId(String orderId);
    List<OrderPO> findByOrderStatus(OrderStatusEnum orderStatus);
}
