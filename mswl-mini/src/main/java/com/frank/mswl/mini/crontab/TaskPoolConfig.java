package com.frank.mswl.mini.crontab;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步任务线程池设置.
 *
 * @author 张孝党 2019/10/08.
 * @version V1.01.
 * <p>
 * 更新履历： V1.00 2019/10/08 张孝党 创建.
 */
@Slf4j
@Configuration
public class TaskPoolConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {

        log.info("异步线程池配置开始.............................");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(75);
        executor.setMaxPoolSize(150);
        executor.setQueueCapacity(300);
        executor.setKeepAliveSeconds(5 * 60);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(5 * 60);

        log.info("异步线程池配置结束.............................");
        return executor;
    }
}
