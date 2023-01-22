package io.ab.shop.user.config;

import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Description  Sleuth异步线程池配置,
 * @Return
 * @Date 2023/1/19 18:36
 */
@Configuration
@EnableAutoConfiguration
public class ThreadPoolTaskExecutorConfig extends AsyncConfigurerSupport {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("trace-thread-");
        executor.initialize();

        // executor执行异步任务时 Sleuth会重新生成 traceId 和 spanId
//        return executor;
        // 使用 LazyTraceExecutor时执行异步任务只会重新生成 spanId
        return new LazyTraceExecutor(this.beanFactory, executor);
    }

}
