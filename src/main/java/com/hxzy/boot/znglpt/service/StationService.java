package com.hxzy.boot.znglpt.service;

import java.util.List;

import com.hxzy.boot.znglpt.entity.Station;

public interface StationService {
	public List<Station> list();
	
	public Station getStation(String name);
	
	public void delStation();
}
