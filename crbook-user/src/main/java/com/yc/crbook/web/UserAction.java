package com.yc.crbook.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.crbook.bean.CrUser;
import com.yc.crbook.bean.CrUserExample;
import com.yc.crbook.bean.Result;
import com.yc.crbook.dao.CrUserMapper;

@RequestMapping("/user")
@RestController
public class UserAction {
	
	@Resource
	private CrUserMapper uMapper;

	//feign要求对象参数标注@RequestBody
	@PostMapping("login")
	public Result login(@RequestBody CrUser user) {
		
		CrUserExample cue=new CrUserExample();
		cue.createCriteria().andAccountEqualTo(user.getAccount())
		.andPwdEqualTo(user.getPwd());
		
		List<CrUser> list=uMapper.selectByExample(cue);
	
		if(list.isEmpty()) {
			return new Result(0,"用户名或密码错误！");
		}else {
			//将登录的对象传回
			return new Result(1,"登录成功！",list.get(0));
		}
		
	}
}
