package com.caveofprogramming.spring.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caveofprogramming.spring.web.dao.FormValidationGroup;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.service.UsersService;

@Controller
public class LoginController {

	private UsersService usersService;
	
	@RequestMapping("/login")
	public String showLogin () {
		return "login";
	}
	
	
	@RequestMapping("/denied")
	public String showDenied () {
		return "denied";
	}
	
	@RequestMapping("/logout")
	public String showLogout() {
		return "logout";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount ( Model model) {
		
		model.addAttribute("user", new User());
		return "newaccount";
	}

    @Autowired	
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}



	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createaccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		
		if(result.hasErrors()){		
		
			return "newaccount";
		}
		
		user.setEnabled(true);
		user.setAuthority("ROLE_USER");
		
		if (usersService.exists(user.getUsername() )) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		usersService.create(user);	
		
		/*//try {
			usersService.create(user);	
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username", "This username already exists");
			return "newaccount";
		}*/
		
		
		
		//System.out.println(offer);
		return "accountcreated";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		
		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);
		
		return "admin";
	}
	
}
