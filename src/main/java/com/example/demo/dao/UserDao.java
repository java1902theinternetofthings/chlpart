package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	@Query("select new User(u.id,u.username,u.password) from User u where"
			+ " u.username =:#{#user.username} and  u.password = :#{#user.password}")
	public User getUser(User user);
	
	@Query("select new User(u.id,u.username,u.password) from User u where"
			+ " u.username =?1 and  u.password =?2")
	public User getUser(String username ,String password);
	
}
