package com.example.modeling.repository;

import com.example.modeling.po.PreOrderPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreOrderRepository extends JpaRepository<PreOrderPO, Integer> {
    PreOrderPO findByOrderId(String orderId);
}
