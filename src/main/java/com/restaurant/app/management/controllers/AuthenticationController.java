package com.restaurant.app.management.controllers;

import com.restaurant.app.management.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController extends AbstractController {
	
	@RequestMapping(value = "/registeremployee", method = RequestMethod.GET)
	public String signupForm() {
		return "registeremployee";
	}
	
	@RequestMapping(value = "/registeremployee", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String verify=request.getParameter("verify");

		if (!password.equals(verify)) {
			model.addAttribute("verify_error", "Passwords do not match.");
			model.addAttribute("username", username);
			return "registeremployee";
		} else {
			if (!Employee.isValidUsername(username)) {
				model.addAttribute("username_error", "Username is not valid.");
				return "registeremployee";
				
			} else if (!Employee.isValidPassword(password)){
				model.addAttribute("password_error", "Password is not valid.");
				model.addAttribute("username", username);
				return "registeremployee";
			}
		}

		Employee u= employeeDao.findByUsername(username);
		if (u == null) {
			Employee newUser= new Employee(username, password);
			employeeDao.save(newUser);
			HttpSession thisSession = request.getSession();
			this.setUserInSession(thisSession, newUser); 
			
			return "redirect:/customerlookup";
		} else{
			model.addAttribute("username_error", "That username already exists!");
			return "registeremployee";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if  (username.equals("") && password.equals("")) {
			model.addAttribute("error", "Username and Password are required.");
			model.addAttribute("username", username);
			model.addAttribute("password", password);
			return "login";
		}
		if (username.equals("")) {
			model.addAttribute("error", "Username is required.");
			return "login";
		} 
		if (password.equals("")) {
			model.addAttribute("error", "Password is required.");
			model.addAttribute("username", username);
			return "login";
		}
		
		Employee u= employeeDao.findByUsername(username);
		if (u == null) {
			model.addAttribute("error", "Login failed. Username/Password does not exist.");
			model.addAttribute("username", username);
			return "login";
		}
		System.out.println(Employee.hashPassword(password)+ " " + u.getPwHash());
		if (u.isMatchingPassword(password)) {
			HttpSession thisSession = request.getSession();
			this.setUserInSession(thisSession, u); 
		//	thisSession.setAttribute("user", u);
		} else {
			model.addAttribute("error", "Login failed. Username/Password does not exist.");
			model.addAttribute("username", username);
			return "login";
		}
				
		return "redirect:/customerlookup";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}
