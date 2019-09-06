package com.example.modeling.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class PayServiceImpl implements PayService {
    public boolean pay(BigDecimal value) {
        long current = new Date().getTime();
        String payId = String.valueOf(current);
        log.info("paid {} {}", payId, value);
        return true;
    }
}
