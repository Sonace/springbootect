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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	
	
	@PostMapping(value = { "/submit", "/henho/submit" })
	@ResponseBody
	public void submit(@RequestParam(value="IdDel") Integer[] idDel) {
			for (Integer integer : idDel) {
				System.out.println(integer);
			}
		
	}
	
	
	
	@GetMapping(value = { "/", "/henho" })
	public String index(Model model, @RequestParam(value = "column", defaultValue = "") String column,
			@RequestParam(value = "sortt", defaultValue = "0") String sortt) throws ParseException {
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// Set<String> roles = authentication.getAuthorities().stream().map(r ->
		// r.getAuthority())
		// .collect(Collectors.toSet());
		// String[] rolo = roles.toArray(new String[roles.size()]);
		//
		// String role = (rolo[0]);
		// System.out.println(role + " da dang nhap");
		//
		String sql = "";
		String lastTime=column;
		switch (column) {
		case "userName":
			if (sortt.equals("0")) {
				sql = "userName";
				sortt="1";
			} else {
				sql = "userNameDesc";
				sortt="0";
			}
			break;
		case "email":
			if (sortt.equals("0")) {
				sql = "email";
				sortt="1";
			} else {
				sql = "emailDesc";
				sortt="0";
			}
			break;

		case "phone":
			if (sortt.equals("0")) {
				sql = "phone";
				sortt="1";
			} else {
				sql = "phoneDesc";
				sortt="0";
			}
			break;

		case "dob":
			if (sortt.equals("0")) {
				sql = "dob";
				sortt="1";
			} else {
				sql = "dobDesc";
				sortt="0";
			}
			break;

		case "amount":
			if (sortt.equals("0")) {
				sql = "amount";
				sortt="1";
			} else {
				sql = "amountDesc";
				sortt="0";
			}
			break;

		default:
			break;
		}

		List<User> getdata = userRepository.getdata(sql);
		
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat to = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = 0; i < getdata.size(); i++) {

			// String format = formatter.format(getdata.get(i).getBod());
			String reformattedStr = to.format(from.parse(getdata.get(i).getBod()));

			getdata.get(i).setBod(reformattedStr);

		}
		
		model.addAttribute("userData", getdata);
		model.addAttribute("sortt", sortt);
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
		List<User> lu = new ArrayList<User>();
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
