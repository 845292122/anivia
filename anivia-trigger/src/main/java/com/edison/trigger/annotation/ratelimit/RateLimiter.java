package com.edison.trigger.annotation.ratelimit;

import java.lang.annotation.*;

/**
 * @author edison
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    int rate() default 10;
}
