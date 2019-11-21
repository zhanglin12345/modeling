package com.example.modeling.po;

import com.example.modeling.model.OrderStatusEnum;
import lombok.*;

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
    protected Integer id;

    @Column(name = "order_id")
    protected String orderId;

    @Column(name = "name")
    protected String name;

    @Column(name = "amount")
    protected Integer amount;

    @Column(name = "price")
    protected BigDecimal price;

    @Column(name = "total")
    protected BigDecimal total;

    @Column(name = "order_status", columnDefinition = "enum")
    @Enumerated(EnumType.STRING)
    protected OrderStatusEnum orderStatus;

    @Column(name = "pre_money")
    private BigDecimal preMoney;

    @Column(name = "partial_pay_time")
    private long partialPayTime;

    @Column(name = "pay_all_money_delay")
    private long payAllMoneyDelay;
}
