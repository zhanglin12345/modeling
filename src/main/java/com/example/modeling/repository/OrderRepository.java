package com.example.modeling.repository;

import com.example.modeling.po.OrderPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderPO, Integer> {
    OrderPO findByOrderId(String orderId);
}
