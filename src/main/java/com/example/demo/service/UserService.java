package com.example.demo.service;


import com.example.demo.entity.User;

public interface UserService {
	
	public User getUser(User user);
	
	public User getUser(String username ,String password);
	
}
