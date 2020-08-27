package com.zhi.order.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderAction {
	
	@Resource
	private RestTemplate restTemplate;

	@GetMapping("order")
	public String user(HttpServletRequest req) {
		return String.format("server:cloud-order;ip:%s;port:%s", req.getLocalAddr(),req.getLocalPort());
	}
	
	@GetMapping("user")
	public String order() {
		String url="http://127.0.0.1:8082/user";
		String res=restTemplate.getForObject(url, String.class);
		return res;
	}
}
