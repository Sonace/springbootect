package com.SpringBootect.son.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.SpringBootect.son.model.Order;
import com.SpringBootect.son.model.User;

public interface UserMapper {
	public List<User> selectAllUser();

	public User findUserAccount(String userName);

	public int insertUser(User user);

	public int insertTable(User user);

	public int updateTable(User user);

	public List<User> getdata(Order order);

	public int deleteUserById(int userId);

	public int deleteAccountById(int userId);

	public int deleteAddressById(int userId);

}
