package com.dobatii.dockerization1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;

/**
 * Enabling asynchronous process support 
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-17
 * 
 */

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig {
	
	// Inject properties values for the custom TaskExecutor
	@Value("${province.corePoolSize}")
	private Integer corePoolSize;
	
	@Value("${province.queueCapacity}")
	private Integer queueCapacity;
	
	@Value("${province.maxPoolSize}")
	private Integer maxPoolSize;
	
	@Value("${province.threadNamePrefix}")
	private String threadNamePrefix;
	
	/**
	 * This method uses TaskExecutorBuilder to construct a custom TaskExecutor for the province service
	 * 
	 * @param builder
	 * @return a custom taskExecutor
	 * 
	 */
	@Bean
	@Primary
	public TaskExecutor provinceTaskExecutor(TaskExecutorBuilder builder) {
		log.info("properties values for the custom TaskExecutor : {}, \n{}, \n{}, \n{}".toUpperCase(), corePoolSize, queueCapacity, maxPoolSize, threadNamePrefix);
		return builder.corePoolSize(corePoolSize)
				.maxPoolSize(maxPoolSize)
				.queueCapacity(queueCapacity)
				.threadNamePrefix("province-exec-")
				.build();
	}
}
