package com.zhi.user.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserAction {

	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping("user")
	public String user(HttpServletRequest req) {
		return String.format("server:cloud-user;ip:%s;port:%s", req.getLocalAddr(),req.getLocalPort());
	}
	
	/**
	 * 127.0.0.1:8082==>用户地址
	 * 浏览器测试地址：http://127.0.0.1:8082/order
	 * order替代127.0.0.1:8082
	 * @return
	 */
	@GetMapping("order")
	public String order() {
		//String url="http://127.0.0.1:8083/order";
		String url="http://order/order";
		String res=restTemplate.getForObject(url, String.class);
		return res;
	}
	
	@Resource
	private IOrderAction iod;
	
	@GetMapping("order1")
	public String order1() {
		return iod.order();
	}
	
}
