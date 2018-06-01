package com.SpringBootect.son.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringBootect.son.config.AppConfig;
import com.SpringBootect.son.model.User;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	static AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	static DefaultSqlSessionFactory sessionFactory = (DefaultSqlSessionFactory) context.getBean("sqlSessionFactory");

	private SqlSessionFactory sqlSessionFactory = sessionFactory;
	
	
	
	
	 public static String encrytePassword(String password) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder.encode(password);
	    }
	

	@Override
	public List<User> selectAllUser() {
		SqlSession session = sqlSessionFactory.openSession();
		List<User> selectList = session.selectList("selectAllUser");
		return selectList;
	}

	@Override
	public User findUserAccount(String userName) {
		SqlSession session = sqlSessionFactory.openSession();
		User user = (User) session.selectOne("findUserAccount", userName);
		return user;
	}

	@Override
	public int insertUser(User user) {
		SqlSession session = sqlSessionFactory.openSession();
		
		String encrytePassword = encrytePassword(user.getPass_word());
		user.setPass_word(encrytePassword);
		try {
			int insert = session.insert("insertUser", user);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
		
		
		
	}

	public static void main(String[] args) {

		// insert student
		User u= new User();
		u.setUser_name("qweqwe");
		u.setPass_word("asdasdasd");
		
		
       
		
		
		System.out.println("insert : " + new UserServiceImpl().findUserAccount("son@gmail.com"));
		
		
		System.out.println("asdasdas"+new UserServiceImpl().insertUser(u));
	}

}
