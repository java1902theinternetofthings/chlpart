package com.hxzy.boot.znglpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxzy.boot.znglpt.dao.StationDao;
import com.hxzy.boot.znglpt.entity.Station;
import com.hxzy.boot.znglpt.service.StationService;

@Service
public class StationServiceImpl implements StationService{
	@Autowired
	private StationDao stationDao;

	@Override
	public List<Station> list() {
		// TODO Auto-generated method stub
		return stationDao.findAll();
	}

	@Override
	public Station getStation(String name) {
		// TODO Auto-generated method stub
		return stationDao.getStation(name);
	}

	@Override
	public void delStation() {
		// TODO Auto-generated method stub
		
	}

}
