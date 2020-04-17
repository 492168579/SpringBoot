package com.yzy.could.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yzy.could.dao.UserDao;
import com.yzy.could.entity.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	@Autowired
	private UserDao userDao;

	@Test
	public void testSaveUser() throws Exception {
		UserEntity user = new UserEntity();
		user.setId(2l);
		user.setUserName("小明");
		user.setPassWord("fffooo123");
		userDao.saveUser(user);
	}

	@Test
	public void findUserByUserName() {
		UserEntity user = userDao.findUserByUserName("小明");
		System.out.println("user is " + user);
	}

	@Test
	public void updateUser() {
		UserEntity user = new UserEntity();
		user.setId(2l);
		user.setUserName("天空");
		user.setPassWord("fffxxxx");
		userDao.updateUser(user);
	}

	@Test
	public void deleteUserById() {
		userDao.deleteUserById(1l);
	}

}
