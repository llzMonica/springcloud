package com.yc.crbook;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yc.crbook.bean.User;
import com.yc.crbook.bean.UserExample;
import com.yc.crbook.dao.UserMapper;

@SpringBootTest
public class CrUserAplicationTest {

	@Resource
	UserMapper uMapper;
	
	@Test
	public void contexLoads() {

		// MyBatis 方向工程的接口的使用
		
		// 组合条件查询 <if>  ==> Example 对象构建条件
		
		UserExample ue=new UserExample();
		ue.createCriteria()
		.andAccountEqualTo("admin")
		.andPwdEqualTo("111");
		
		List<User> list=uMapper.selectByExample(ue);
		
		assertEquals(1, list.size());
		
	
	
	}
	
}
