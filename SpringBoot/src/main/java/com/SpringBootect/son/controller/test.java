package com.SpringBootect.son.controller;

import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.SpringBootect.son.model.Address;
import com.SpringBootect.son.model.User;
import com.SpringBootect.son.service.AddressService;
import com.SpringBootect.son.service.RoleService;
import com.SpringBootect.son.service.UserService;

@Controller
public class test {

	@Autowired
	private UserService userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private JavaMailSender emailSender;

	@GetMapping(value = { "/", "/henho" })
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
	public String getLogin() {
		return "login";
	}

	@RequestMapping("/register")
	public String register(Model model) {
		User u = new User();
		model.addAttribute("User", u);
		return "register";
	}

	@RequestMapping(value = "/registerProccessing", method = RequestMethod.POST)
	public String registerProccessing(@ModelAttribute(value = "User") @Valid User user, BindingResult bindingResult,
			Model model) {
		
		

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
			return "register";

		}
		int insertUser = userRepository.insertUser(user);
		System.out.println(insertUser);
		if (insertUser == 1) {
			Address a = new Address();
			a.setUser_id(user.getAddress().getUser_id());
			a.setEmail(user.getAddress().getEmail());
			a.setFax(user.getAddress().getFax());
			a.setPhone_number(user.getAddress().getPhone_number());
			addressService.insertAddress(a);
			System.out.println("ok");

			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			try {
				helper.setTo(user.getAddress().getEmail());
				helper.setText("Hi" + "good to meet you");
				helper.setSubject("Register successfuly");

			} catch (MessagingException e) {

				e.printStackTrace();
			}

			emailSender.send(message);

			return "/login";
		} else {
			System.out.println("xit");
			return "/login";
		}

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
