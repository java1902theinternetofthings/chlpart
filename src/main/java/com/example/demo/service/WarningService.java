package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Warning;

public interface WarningService {
	public List<Warning> getWarning();
	
	public Warning getWarningById(Integer id);
	
	public void changeEffect(Integer id);
	
	public void delWarning(Integer id);
	
	public void dealEffect(Integer id);
	
	public Page<Warning> listWarning(int page,int size);
	
	public Warning searchWarningByClassification(String classification,
			String townshipname,String villagename,String sitename,
			String effectiveness);
}
