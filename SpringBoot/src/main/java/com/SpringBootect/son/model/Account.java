package com.SpringBootect.son.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {
	private int account_id;
	private double amount;
	private int user_id;
	
	

}
