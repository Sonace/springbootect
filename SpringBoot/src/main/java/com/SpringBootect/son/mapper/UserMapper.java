package com.SpringBootect.son.mapper;
import java.util.List;



import com.SpringBootect.son.model.User;

public interface UserMapper {
	public List<User> selectAllUser();
    public User findUserAccount(String userName);
    public int insertUser(User user);
}
