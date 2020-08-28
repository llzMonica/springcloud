package com.zhi.order.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user",fallback = UserActionHystrix.class) //网元名称  服务名
public interface IUserAction {

	@GetMapping("user")
	public String user();
}
