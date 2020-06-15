package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.WorkSheetDao;
import com.example.demo.entity.WorkSheet;
import com.example.demo.service.WorkSheetService;
@Service
public class WorkSheetServiceImpl implements WorkSheetService{
	@Autowired
	private WorkSheetDao workSheetDao;
	
	
	
	@Override
	public void addsheet(WorkSheet wsheet) {
		// TODO Auto-generated method stub
		workSheetDao.save(wsheet);
	}

}
