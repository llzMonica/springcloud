package com.yc.crbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
//服务降级注解
@EnableCircuitBreaker
//开启声明式服务调用
@EnableFeignClients
@MapperScan("com.yc.crbook.dao")
@SpringBootApplication
public class CrBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrBookApplication.class, args); 
	}
  
	/**
	 * 定义RestTemplate bean
	 * @return  
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}
