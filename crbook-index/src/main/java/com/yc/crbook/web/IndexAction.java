package com.yc.crbook.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yc.crbook.bean.CrBook;
import com.yc.crbook.bean.CrShow;
import com.yc.crbook.web.remote.IBookAction;

@Controller
public class IndexAction {
	
	@Resource
	private IBookAction baction;

	
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

	@GetMapping(path = {"login","login.html"})
	public String login() {
		
		return "login";
	}
}
