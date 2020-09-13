package com.yc.crbook.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAction {

	@RequestMapping("/user")
	public String user() {
		return "user";
	}
}
