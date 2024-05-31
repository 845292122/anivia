package com.edison.trigger.annotation.ratelimit;

import com.edison.framework.exception.BizException;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author edison
 */
@Aspect
@Component
public class RateLimitAspect {

    private final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    @Around("@annotation(com.edison.trigger.annotation.ratelimit.RateLimiter)")
    public Object limitRate(ProceedingJoinPoint joinPoint, com.edison.trigger.annotation.ratelimit.RateLimiter rateLimited) throws Throwable {
        String key = createKey(joinPoint);
        RateLimiter rateLimiter = getOrCreateRateLimiter(rateLimited.rate());
        if (!rateLimiter.tryAcquire()) {
            throw new RuntimeException("服务器正忙,请稍后再试.");
        }
        return joinPoint.proceed();
    }

    private String createKey(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private RateLimiter getOrCreateRateLimiter(int permitsPerSecond) {
        return rateLimiterMap.computeIfAbsent(String.valueOf(permitsPerSecond), k -> RateLimiter.create(permitsPerSecond));
    }
}
