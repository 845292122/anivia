package com.edison.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * 异步任务配置
 * 使用方法:
 * @Async("threadName"): 定义来类上或者方法上
 *  异步方法返回值只能是void或Future
 *  必须是public方法
 *  必须是spring管理的bean
 *  不能内部调用方法
 *
 *
 * @author edison
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    private final String threadName = "async-executor";

    /**
     * 自定义线程池
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors() * 2);
        executor.setThreadNamePrefix(threadName);
        executor.initialize();
        return executor;
    }

    /**
     * 线程处理
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (Throwable ex, Method method, Object... params) -> {
            log.error("异步任务异常: {}", ex.getMessage());
        };
    }
}
