package com.yzy.could.service;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yzy.could.dao.UserMapper;
import com.yzy.could.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {
	@Autowired
	private UserMapper userMapper ;
	  @Test
	  @Transactional
	   public void  testinsert(){
		   User user  = new User();
		   user.setId(7L);
		   user.setUserName("user8");
		   user.setBalance(new BigDecimal("800.00"));
		   user.setAge(Short.valueOf("18"));
		   user.setName("叶兆一");
		   userMapper.insert(user);
	      try {
		   int i = 1/0 ;
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	   }
	   @Ignore
	   @Test
	    public void testFindUserByUserName(){
	        User user = userMapper.findUserByUserName("user8");
	        System.out.println(user.getName());
	    }

	

}
