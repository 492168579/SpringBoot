package com.yzy.could.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzy.could.dao.UserMapper;
import com.yzy.could.entity.User;
import com.yzy.could.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper ;

	@Override
	@Transactional
	public void insert(User user) {
		userMapper.insert(user);
	}

}
