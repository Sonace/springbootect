package com.SpringBootect.son.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;

	@NotNull
//	@Pattern(regexp = "^[0-9\\s- \\+]{8,13}$")

	private int phone_number;
	
	@Email
	private String email;
	
	@NotNull
//	@Pattern(regexp = "^[0-9\\s- \\+]{8,13}$")

	private int fax;
}
