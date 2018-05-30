package com.SpringBootect.son.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import com.SpringBootect.son.config.AppConfig;
import com.SpringBootect.son.model.User;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	static AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	static DefaultSqlSessionFactory sessionFactory = (DefaultSqlSessionFactory) context.getBean("sqlSessionFactory");

	private SqlSessionFactory sqlSessionFactory = sessionFactory;

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

	public static void main(String[] args) {

		// insert student

		System.out.println("insert : " + new UserServiceImpl().findUserAccount("son@gmail.com"));
	}

}
