package com.example.modeling.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Override
    public boolean lockMoney(String account, BigDecimal value, long expireDuration) {
        log.info("{}'s money {} locked for {}", account, value, expireDuration);
        return true;
    }

    /*
    * if pay failed, then throw exception
    * */
    @Override
    public boolean pay(String account, BigDecimal value) {
        long current = new Date().getTime();
        String payId = String.valueOf(current);
        log.info("paid {} {}", payId, value);
        return true;
    }

    @Override
    public boolean unLockMoney(String account, BigDecimal value) {
        log.info("{}'s money {} unlocked", account, value);
        return true;
    }

    @Override
    public boolean confirmMoneyPaid(String account, BigDecimal value) {
        log.info("{}'s money {} confirmed paid", account, value);
        return true;
    }
}
