package com.SpringBootect.son.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.ibatis.javassist.bytecode.analysis.Util;
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

	private List<User> lu;

	@GetMapping(value = { "/", "/henho" })
	public String index(Model model) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority())
				.collect(Collectors.toSet());
		String[] rolo = roles.toArray(new String[roles.size()]);

		String role = (rolo[0]);
		System.out.println(role + " da dang nhap");
		

		List<User> getdata = userRepository.getdata();
	
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat to = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = 0; i < getdata.size(); i++) {
			
//			String format = formatter.format(getdata.get(i).getBod());
			String reformattedStr = to.format(from.parse(getdata.get(i).getBod()));
		
			getdata.get(i).setBod(reformattedStr); 
		
		}
			
		model.addAttribute("userData", getdata);
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
		List<User> lu= new ArrayList<User>();
		lu = userRepository.selectAllUser();
		
		
		model.addAttribute("User", u);
		model.addAttribute("lUser", lu);
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
