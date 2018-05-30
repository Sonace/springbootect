package com.SpringBootect.son.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class makepass {
	
	 public static String encrytePassword(String password) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder.encode(password);
	    }
	 
	    public static void main(String[] args) {
	        String password = "ngovanson";
	        String encrytedPassword = encrytePassword(password);
	 
	        System.out.println("Encryted Password: " + encrytedPassword);
	    }

}
