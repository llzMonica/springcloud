package com.yc.crbook.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yc.crbook.bean.CrBook;
import com.yc.crbook.web.remote.IBookAction;

@Controller
public class IndexAction {
	
	@Resource
	private IBookAction baction;

	
	@GetMapping("/")
	public String index(Model m) {
		
		List<CrBook> book=baction.getNewBooks();
		
		m.addAttribute("newBooks", book);
		
		return "index";
	}

}
