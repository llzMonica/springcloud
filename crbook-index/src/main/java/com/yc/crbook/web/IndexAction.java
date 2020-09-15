package com.yc.crbook.web;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yc.crbook.bean.CrBook;
import com.yc.crbook.bean.CrShow;
import com.yc.crbook.bean.CrUser;
import com.yc.crbook.bean.Result;
import com.yc.crbook.web.remote.IBookAction;
import com.yc.crbook.web.remote.IUserAction;

@Controller
public class IndexAction {
	
	@Resource
	private IBookAction baction;

	@Resource
	private IUserAction uaction;
	
	@GetMapping("/")
	public String index(Model m) {
		
		//最新上架
		List<CrBook> newBooks=baction.getNewBooks();
		//首页的第一个编辑推荐
		List<CrShow> recom1=baction.getRecom1();
		
		m.addAttribute("newBooks", newBooks);
		m.addAttribute("recom1", recom1);
		
		return "index";
	}

	@GetMapping(path = {"tologin","login.html"})
	public String tologin() {
		return "login";
	}
	
	@PostMapping("login")
	public String login(@Valid CrUser user,Errors errors,Model m) {
		//验证用户输入的数据是否正确
		if(errors.hasErrors()){
			//如果错误，跳转回登录
			m.addAttribute("errors", errors.getFieldError());
			return "login";
		}
		//发起远程服务调用，传递两个参数(用户名，密码)
		Result res=uaction.login(user);		
		//根据返回的结果，如果正确跳转首页
		if(res.getCode()==1) {
			return index(m);
		}else {
			//自定义一个错误
			errors.rejectValue("account", "AccountOrPwdError","用户名或密码错误");
			m.addAttribute("errors", errors.getFieldError());
			//如果错误，跳转回登录页
			return "login";
		}
		
	}
}
