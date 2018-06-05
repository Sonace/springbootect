package com.SpringBootect.son.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	
	@NotNull
	private String user_name;

	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$")
	private String pass_word;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String bod;
	
	@Valid
	private Account account;
	@Valid
	private Address address;

	

	@Override
	public String toString() {
		return getClass().getName() +  "User [user_id=" + user_id + ", user_name=" + user_name + ", pass_word=" + pass_word + ", bod=" + bod
				+ ", account=" + account + ", address=" + address + "]";
	}

	
	

}
