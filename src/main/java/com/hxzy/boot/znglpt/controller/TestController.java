package com.hxzy.boot.znglpt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hxzy.boot.znglpt.entity.Station;
import com.hxzy.boot.znglpt.service.StationService;

@Controller
public class TestController {
	@Autowired
	private StationService stationService;
	
	@PostMapping("/login")
	public String login(@ModelAttribute Station station,HttpSession session) {
		
		return null;
	}
}
