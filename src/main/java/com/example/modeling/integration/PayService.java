package com.example.modeling.integration;

import java.math.BigDecimal;

public interface PayService {
    boolean lockMoney(String account, BigDecimal value, long expireDuration);
    boolean pay(String account, BigDecimal value);
    boolean unLockMoney(String account, BigDecimal value);
    boolean confirmMoneyPaid(String account, BigDecimal value);
}
