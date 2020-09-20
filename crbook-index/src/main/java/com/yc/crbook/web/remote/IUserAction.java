package com.yc.crbook.web.remote;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.crbook.bean.CrBook;
import com.yc.crbook.bean.CrCart;
import com.yc.crbook.bean.CrShow;
import com.yc.crbook.bean.CrUser;
import com.yc.crbook.bean.Result;

//声明式服务调用的接口
@FeignClient("cruser")
public interface IUserAction {

	@PostMapping("user/login")
	Result login(@RequestBody CrUser user);

	@PostMapping("user/register")
	Result register(@RequestBody CrUser user);
	
	@GetMapping("cart/findByUid")
	List<CrCart> findByUid(@RequestParam Integer uid);
}
