package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Station;

public interface StationService {
	public List<Station> list();
	
	public Station getStation(String name);
	
	public void delStation();
}
