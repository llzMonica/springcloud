package com.zhi.zuul.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAction {

	@GetMapping("/test")
	public String test() {
		System.out.println("test============");
		return "test";
	}
}
