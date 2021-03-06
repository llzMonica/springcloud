package com.yc.crbook.web.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yc.crbook.bean.CrBook;
import com.yc.crbook.bean.CrShow;
import com.yc.crbook.bean.CrUser;
import com.yc.crbook.bean.Result;

//声明式服务调用的接口
@FeignClient("crbook")
public interface IBookAction {

	@GetMapping("book/getNewBooks")
	List<CrBook> getNewBooks();
	
	@GetMapping("book/getRecom1")
	List<CrShow> getRecom1();
	
	@PostMapping
	Result login(CrUser user);
}
