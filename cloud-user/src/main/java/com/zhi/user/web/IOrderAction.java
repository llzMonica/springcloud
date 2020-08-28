package com.zhi.user.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order") //网元名称  服务名
public interface IOrderAction {

	@GetMapping("order")
	public String order();
}
