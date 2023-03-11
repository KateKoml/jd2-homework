package com.komleva.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Map;
import java.util.TreeMap;

@Component
@Aspect
public class TimeMeasureAspect {
    private static final Logger log = Logger.getLogger(TimeMeasureAspect.class);

    private static final Map<String, Integer> calledOutCounter = new TreeMap<>();

    @Pointcut("execution(* com.komleva.repository.*.*(..))")
    public void methodWorkingTimePointcut() {
    }

    @Pointcut("execution(* com.komleva.repository.*.*(..))")
    public void methodCalledOutTimePointcut() {
    }

    @Around("methodWorkingTimePointcut()")
    public Object logWorkingTimeOfMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        log.info("Method " + joinPoint.getSignature().getName() + " working time: " + stopWatch.getLastTaskTimeMillis() + " ms");
        return proceed;
    }

    @Around("methodCalledOutTimePointcut()")
    public Object logMethodCalledOutTimePointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        String path = joinPoint.getSignature().getDeclaringType().getName() + ":" + joinPoint.getSignature().getName() + "()";
        if (calledOutCounter.containsKey(path)) {
            calledOutCounter.put(path, +calledOutCounter.get(path) + 1);
        } else {
            calledOutCounter.put(path, 1);
        }
        Object proceed = joinPoint.proceed();
        log.info("Method " + joinPoint.getSignature().getName() + " has been called " + calledOutCounter.get(path) + " times");
        return proceed;
    }
}
