package com.SpringBootect.son.service;

import java.util.List;

import com.SpringBootect.son.model.User;

public interface UserService {
	public List<User> selectAllUser();

	public User findUserAccount(String userName);

	public int insertUser(User user);

	public List<User> getdata(String sort);
}
