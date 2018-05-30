package com.SpringBootect.son.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;



public interface RoleMapper {
	public List<String >getRoleNames(int userId);
}
