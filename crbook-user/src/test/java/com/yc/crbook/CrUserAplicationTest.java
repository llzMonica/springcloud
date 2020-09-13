package com.yc.crbook;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yc.crbook.bean.CrUser;
import com.yc.crbook.bean.CrUserExample;
import com.yc.crbook.dao.CrUserMapper;

@SpringBootTest
public class CrUserAplicationTest {

	@Resource
	CrUserMapper uMapper;
	
	@Test
	public void contexLoads() {

		// MyBatis 方向工程的接口的使用
		
		// 组合条件查询 <if>  ==> Example 对象构建条件
		
		CrUserExample cue=new CrUserExample();
		cue.createCriteria()
		.andAccountEqualTo("admin")
		.andPwdEqualTo("111");
		
		List<CrUser> list=uMapper.selectByExample(cue);
		
		assertEquals(1, list.size());
		
	
	
	}
	
}
