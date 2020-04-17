package com.yzy.could.dao;

import com.yzy.could.entity.UserEntity;

public interface UserDao {

	void saveUser(UserEntity user);

	UserEntity findUserByUserName(String userName);

	void updateUser(UserEntity user);

	void deleteUserById(Long id);

}
