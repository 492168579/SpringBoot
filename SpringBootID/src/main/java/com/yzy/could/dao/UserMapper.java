package com.yzy.could.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yzy.could.entity.User;

@Mapper
public interface UserMapper{
	@Insert("INSERT INTO WX_USER(ID,USER_NAME,AGE,BALANCE,NAME) VALUES(#{id},#{userName},#{age},#{balance},#{name})")
	public void insert(User user);
    @Delete("DELETE FROM WX_USER WHERE ID = #{id}")
    public int deleteById(int id);
	@Update("update WX_USER set adminName=#{adminName} where id=#{id}")
    public int update(User user);
	@Select("SELECT * FROM WX_USER WHERE USER_NAME = #{userName}")
	public User findUserByUserName(@Param("userName") String userName);

}
