package com.zhi.user.web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.checkerframework.checker.units.qual.s;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserAction {

	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping("user")
	public String user(HttpServletRequest req,HttpServletResponse res,HttpSession session) {
		
		System.out.println(req.getHeader("Cookie"));
		
		System.out.println(req.getHeader("Authorization"));
		
		if(session.getAttribute("loginedUser")==null) {
			session.setAttribute("loginedUser", "100");
			System.out.println("===========用户未登录==========");
		}else {
			System.out.println("===========用户已登录==========");
		}
		
		//添加cookie  ==》Set-Cookie
		Cookie cookie=new Cookie("test","test");
		res.addCookie(cookie);
		
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
