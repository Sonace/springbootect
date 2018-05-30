package com.SpringBootect.son.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import com.SpringBootect.son.config.AppConfig;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	static AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    static DefaultSqlSessionFactory sessionFactory = (DefaultSqlSessionFactory) context.getBean("sqlSessionFactory");
  
	
	
	 private SqlSessionFactory sqlSessionFactory = sessionFactory;
	 
	@Override
	public List<String> getRoleNames(int userId) {
		SqlSession session = sqlSessionFactory.openSession();
		List<String> selectList = session.selectList("getRoleNames",userId);
        return selectList;
		
	}
	public static void main(String[] args) {
    	
		 
        
        // insert student
   	
        System.out.println("insert : " + new RoleServiceImpl().getRoleNames(1));
   }

}
