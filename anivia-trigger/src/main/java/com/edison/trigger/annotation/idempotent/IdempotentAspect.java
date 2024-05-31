package com.edison.trigger.annotation.idempotent;

import cn.dev33.satoken.stp.StpUtil;
import com.edison.framework.exception.BizException;
import com.github.benmanes.caffeine.cache.Cache;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class IdempotentAspect {

  @Autowired
  @Qualifier("idempotentCache")
  Cache<String, Object> idempotentCache;

  @Around("@annotation(com.edison.trigger.annotation.idempotent.Idempotent)")
  public Object idempotentHandle(ProceedingJoinPoint joinPoint) throws Throwable {

    Object[] args = joinPoint.getArgs();
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    Idempotent idempotent = method.getAnnotation(Idempotent.class);
    String key = getKey(joinPoint);
    StringBuilder nowParams = new StringBuilder();
    for (Object arg : args) {
      if (Objects.nonNull(arg)) {
        nowParams.append(arg.toString()).append(":");
      }
    }

    Object ifPresent = idempotentCache.getIfPresent(key);
    if (ifPresent != null) {
      if (StringUtils.equals(nowParams.toString(), ifPresent.toString())) {
        throw new BizException(idempotent.message());
      }
    }

    idempotentCache.asMap().put(key, nowParams.toString());
    return joinPoint.proceed(args);
  }

  private String getKey(ProceedingJoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    String name = method.getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    String loginId = StpUtil.getLoginIdAsString();
    StringBuilder sb = new StringBuilder();
    StringBuilder key = sb.append(loginId).append(":").append(className).append(":").append(name);
    return key.toString();
  }

}
