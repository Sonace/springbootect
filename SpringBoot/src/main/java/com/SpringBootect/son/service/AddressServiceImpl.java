package com.SpringBootect.son.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import com.SpringBootect.son.config.AppConfig;
import com.SpringBootect.son.model.Address;
@Service
public class AddressServiceImpl implements AddressService {
	
	static AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	static DefaultSqlSessionFactory sessionFactory = (DefaultSqlSessionFactory) context.getBean("sqlSessionFactory");

	private SqlSessionFactory sqlSessionFactory = sessionFactory;


	@Override
	public int insertAddress(Address adress) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int insert = session.insert("insertAddress", adress);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

}
