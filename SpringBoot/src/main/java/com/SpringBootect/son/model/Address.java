package com.SpringBootect.son.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

	private int user_id;
	@NotNull 
	private int phone_number;
	private String email;
	private int fax;
}
