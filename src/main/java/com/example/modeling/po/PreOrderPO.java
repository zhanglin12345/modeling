package com.example.modeling.po;

import com.example.modeling.model.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "pre_order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreOrderPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "order_status")
    private OrderStatusEnum orderStatus;

    @Column(name = "pre_money")
    private BigDecimal preMoney;

    @Column(name = "partial_pay_time")
    private long partialPayTime;

    @Column(name = "pay_all_money_delay")
    private long payAllMoneyDelay;
}
