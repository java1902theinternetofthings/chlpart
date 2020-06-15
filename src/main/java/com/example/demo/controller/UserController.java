package com.example.demo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index() {
		return "loginup";
	}
	
	
	@RequestMapping("/login")
	public String login(HttpSession session, String username,String password) {
		User u = userService.getUser(username, password);
		if(u == null) {
			return "loginup";
		}else {
			session.setAttribute("login", u);
			return "index";
		}
	}

	
}
