package com.yc.crbook.web;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.yc.crbook.bean.CrBook;
import com.yc.crbook.bean.CrShow;
import com.yc.crbook.bean.CrUser;
import com.yc.crbook.bean.Result;
import com.yc.crbook.web.remote.IBookAction;
import com.yc.crbook.web.remote.IUserAction;

@Controller
@SessionAttributes("loginedUser")  
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
	
	/**
	 * 退出登录
	 * SessionStatus是SpringMVC的对象
	 * @param ss
	 * @return
	 */
	@GetMapping(path = {"loginout"})
	public String loginout(SessionStatus ss) {
		ss.setComplete();  //使loginedUser失效
		return "index";
	}
	
	/**
	 * @RestController 控制器存会话，必须获取HttpSession对象
	 * @Controller 控制器会话，使用@SessionAttributes
	 */
	@PostMapping("login")
	public String login(@Valid CrUser user,Errors errors,Model m) {
		//验证用户输入的数据是否正确
		if(errors.hasErrors()){
			//将用户填写的数据传回页面
			m.addAttribute("user", user);
			//如果错误，跳转回登录
			m.addAttribute("errors", errors.getFieldError());
			System.out.println("user"+m.getAttribute("user"));
			return "login";
		}
		//发起远程服务调用，传递两个参数(用户名，密码)
		Result res=uaction.login(user);		
		//根据返回的结果，如果正确跳转首页
		if(res.getCode()==1) {
			m.addAttribute("loginedUser", res.getData());
			return index(m);
		}else {
			//自定义一个错误
			errors.rejectValue("account", "AccountOrPwdError","用户名或密码错误");
			m.addAttribute("errors", errors.getFieldError());
			//如果错误，跳转回登录页
			return "login";
		}
		
	}
	
	@GetMapping(path = {"register.html","toreg"})
	public String toreg() {
		return "register";
	}
	
	@PostMapping("register")
	public String register(@Valid CrUser user,Errors errors,String repwd,Model m) {
		
		if(repwd==null || repwd.trim().isEmpty()) {
			//errors.rejectValue("pwd", "repwdError","请输入确认密码");
			m.addAttribute("repwd","请输入确认密码");
		}else if(repwd.equals(user.getPwd())==false) {
			//errors.rejectValue("pwd", "repwdError","两次输入的密码不一致");
			m.addAttribute("repwd","两次输入的密码不一致");
		}
		if(errors.hasErrors()) {
			//将用户填写的数据传回页面
			m.addAttribute("user", user);
			m.addAttribute("errors", errors.getFieldErrors());
			return "register";
		}
		
		//发起远程服务调用，传递两个参数(用户名，密码)
		Result res=uaction.register(user);		
		//根据返回的结果，如果正确跳转首页
		if(res.getCode()==1) {
			return index(m);
		}else {
			m.addAttribute("errors", errors.getFieldErrors());
			//如果错误，跳转回注册页
			return "register";
		}
		
	}
}
