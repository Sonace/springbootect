package com.SpringBootect.son.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {
	private int user_id;

	private String user_name;

	private String pass_word;

	private Date bod;
	
	
	private Account account;
	private Address address;

	public User(int user_id, String user_name, String pass_word) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.pass_word = pass_word;
	}

	@Override
	public String toString() {
		return getClass().getName() + "User [user_id=" + user_id + ", user_name=" + user_name + ", pass_word="
				+ pass_word + ", bod=" + bod + "]";
	}

}
