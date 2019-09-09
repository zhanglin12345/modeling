package com.example.modeling.po;

import com.example.modeling.model.OrderStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "test_order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "order_id")
    protected String orderId;

    @Column(name = "name")
    protected String name;

    @Column(name = "amount")
    protected int amount;

    @Column(name = "price")
    protected BigDecimal price;

    @Column(name = "total")
    protected BigDecimal total;

    @Column(name = "order_status")
    protected OrderStatusEnum orderStatus;

}
