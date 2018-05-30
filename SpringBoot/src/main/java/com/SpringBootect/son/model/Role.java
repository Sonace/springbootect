package com.SpringBootect.son.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Role implements Serializable {
	private int role_id;
	private String role_name;
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName()+  "Role [role_id=" + role_id + ", role_name=" + role_name + "]";
	}
	
}
