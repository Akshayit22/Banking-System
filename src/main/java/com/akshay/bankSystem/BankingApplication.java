package com.akshay.bankSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executor;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
		System.out.println("Banking Application Running. . . . . . .");
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean("customThreadPoolTaskExecutor")
	Executor getThreadPoolTaskExecutor() {
		
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		
		threadPoolTaskExecutor.setCorePoolSize(4);
		threadPoolTaskExecutor.setMaxPoolSize(4);
		threadPoolTaskExecutor.setQueueCapacity(100);
		threadPoolTaskExecutor.setThreadNamePrefix("My-thread-");
		threadPoolTaskExecutor.initialize();
		
		return threadPoolTaskExecutor;
		
	}

}
