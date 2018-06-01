package com.SpringBootect.son.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int role_id;
	
	@NotNull
	private String role_name;
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName()+  "Role [role_id=" + role_id + ", role_name=" + role_name + "]";
	}
	
}
