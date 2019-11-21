package com.example.modeling.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    // for log only
    @SuppressWarnings({"PMD.UnusedPrivateMethod", "PMD.UncommentedEmptyMethodBody"})
    @Pointcut("execution(* com.example.modeling.biz..*(..))")
    private void logPointcut() {
    }

    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("start execute {}", point.getSignature().getName());
        Object result = point.proceed();
        log.info("end execute {}", point.getSignature().getName());
        return result;
    }
}
