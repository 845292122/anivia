package com.edison.framework.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author edison
 */
@EnableCaching
@Configuration
public class CaffeineConfig {

    /**
     * 认证缓存
     * 
     * @return
     */
    @Bean("authInfoCache")
    public Cache<String, Object> authInfoCache() {
        return Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.DAYS)
                .initialCapacity(100)
                .maximumSize(500)
                .build();
    }

    @Bean("idempotentCache")
    public Cache<String, Object> idempotentCache() {
        return Caffeine.newBuilder().expireAfterWrite(5000, TimeUnit.MILLISECONDS)
                .initialCapacity(100)
                .maximumSize(1000)
                .build();
    }

}
