package com.yc.crbook;

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
@SpringBootApplication
public class CrIndexApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrIndexApplication.class, args); 
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
