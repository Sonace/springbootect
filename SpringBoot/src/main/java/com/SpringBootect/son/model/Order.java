package com.SpringBootect.son.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order implements Serializable {
	private String orderr="";
	private String type="";
	public Order(String sql,String type) {  
	    this.orderr = sql;  
	    this.type = type;  
	}  
	
	
}
