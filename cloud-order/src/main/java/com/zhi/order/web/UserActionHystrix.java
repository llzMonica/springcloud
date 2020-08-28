package com.zhi.order.web;

import org.springframework.stereotype.Component;

@Component
public class UserActionHystrix implements IUserAction{

	@Override
	public String user() {
		return "user服务接口降级回复信息";
	}

	
}
