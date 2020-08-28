package com.zhi.order.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class OrderAction {

	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping("order")
	public String order(HttpServletRequest req) {
		return String.format("server:cloud-order;ip:%s;port:%s", req.getLocalAddr(),req.getLocalPort());
	}
	
	/**
	 * 127.0.0.1:8082==>用户地址
	 * 浏览器测试地址：http://127.0.0.1:8082/order
	 * order替代127.0.0.1:8082
	 * @return
	 */
	@GetMapping("user")
	//服务降级
	@HystrixCommand(fallbackMethod = "fbUser")
	public String user() {
		//String url="http://127.0.0.1:8083/order";
		String url="http://user/user";
		String res=restTemplate.getForObject(url, String.class);
		return res;
	}
	
	public String fbUser() {
		return "user服务接口降级回复信息";
	}
	
	@Resource
	private IUserAction iua;
	
	@GetMapping("user1")
	public String user1() {
		//声明式远程服务调用
		return iua.user();
	}
}
