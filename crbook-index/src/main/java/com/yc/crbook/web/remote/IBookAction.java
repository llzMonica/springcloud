package com.yc.crbook.web.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.yc.crbook.bean.CrBook;

//声明式服务调用的接口
@FeignClient("crbook")
public interface IBookAction {

	@GetMapping("book/getNewBooks")
	List<CrBook> getNewBooks();
}
