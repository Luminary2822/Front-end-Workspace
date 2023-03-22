package com.acreath.gasycp;

import com.acreath.gasycp.util.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@ComponentScan(basePackages = "com.acreath.gasycp")
@MapperScan(value = "com.acreath.gasycp.mapper")
@SpringBootApplication
@EnableAsync
public class GasycpApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(GasycpApplication.class, args);
		SpringContextUtil.setApplicationContext(applicationContext);
	}
	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}
}
