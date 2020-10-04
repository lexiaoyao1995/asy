package com.asy.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * 自定义线程池
 * spring默认
 */
@Configuration
@EnableAsync
public class AsyncCommonConfig extends AsyncConfigurerSupport {
    @Override
    public Executor getAsyncExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        //设置允许同时执行的线程数为10
        executor.setConcurrencyLimit(10);
        return executor;
    }
}