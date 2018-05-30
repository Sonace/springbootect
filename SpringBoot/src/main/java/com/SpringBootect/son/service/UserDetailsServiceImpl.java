package com.SpringBootect.son.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SpringBootect.son.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	//
	// @Override
	// public UserDetails loadUserByUsername(String username) throws
	// UsernameNotFoundException {
	// // TODO Auto-generated method stub
	// User user = userService.findUserAccount(username);
	// if (user == null) {
	// System.out.println("User not found! " + username);
	// throw new UsernameNotFoundException("User not found");
	// }
	//
	// List<GrantedAuthority> grantedAuthorities = new
	// ArrayList<GrantedAuthority>();
	// List<String> roles = roleService.getRoleNames(user.getUser_id());
	// if (roles != null) {
	// for (String role : roles) {
	// // ROLE_USER, ROLE_ADMIN,..
	// GrantedAuthority authority = new SimpleGrantedAuthority(role);
	// grantedAuthorities.add(authority);
	// }
	// }
	//
	// UserDetails userDetails = (UserDetails) new
	// org.springframework.security.core.userdetails.User(user.getUser_name(),
	// user.getPass_word(),
	// grantedAuthorities);
	//
	// return userDetails;
	// }
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = this.userService.findUserAccount(userName);

		if (user == null) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}

		System.out.println("Found User: " + user);

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.roleService.getRoleNames(user.getUser_id());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUser_name(), //
				user.getPass_word(), grantList);

		return userDetails;
	}

}
