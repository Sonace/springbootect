package com.SpringBootect.son.controller;

import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringBootect.son.service.RoleService;
import com.SpringBootect.son.service.UserService;

@Controller
public class test {

	@Autowired
	private UserService userRepository;

	@Autowired
	private RoleService roleService;

	@GetMapping(value = {"/","/henho"})
	public String index() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority())
				.collect(Collectors.toSet());
		String[] rolo = roles.toArray(new String[roles.size()]);

		String role = (rolo[0]);
		System.out.println(role + " da dang nhap");
		return "welcome";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}

	@GetMapping("/login")
	public String getLogin(Model model) {

		model.addAttribute("hoho", userRepository.findUserAccount("son@gmail.com"));
		System.out.println(userRepository.findUserAccount("son@gmail.com"));
		System.out.println(roleService.getRoleNames(1));
		return "login";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}

}
