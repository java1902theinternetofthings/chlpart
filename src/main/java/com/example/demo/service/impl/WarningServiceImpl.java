package com.example.demo.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.WarningDao;
import com.example.demo.entity.Warning;
import com.example.demo.service.WarningService;

@Service
public class WarningServiceImpl implements WarningService{
	
	@Autowired
	private WarningDao warningDao;

	@Override
	public List<Warning> getWarning() {
		// TODO Auto-generated method stub
		return warningDao.findAll();
	}

	@Override
	public Warning getWarningById(Integer id) {
		// TODO Auto-generated method stub
		return warningDao.getWarningById(id);
	}

	@Override
	public void changeEffect(Integer id) {
		// TODO Auto-generated method stub
		Warning warning = warningDao.getWarningById(id);
		warning.setEffectiveness("无效告警");
		warningDao.save(warning);
	}

	@Override
	public void delWarning(Integer id) {
		// TODO Auto-generated method stub
		warningDao.deleteById(id);
	}

	@Override
	public void dealEffect(Integer id) {
		// TODO Auto-generated method stub
		Warning warning = warningDao.getWarningById(id);
		warning.setDeal("已处理");
		warningDao.save(warning);
	}

	@Override
	public Page<Warning> listWarning(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pg = PageRequest.of(page, size);
		return warningDao.findAll(pg);
	}

	@Override
	public Warning searchWarningByClassification(String classification, String townshipname, String villagename,
			String sitename, String effectiveness) {
		// TODO Auto-generated method stub
		return warningDao.searchWarningByClassification(classification, townshipname, villagename, sitename, effectiveness);
	}




	

	
}
