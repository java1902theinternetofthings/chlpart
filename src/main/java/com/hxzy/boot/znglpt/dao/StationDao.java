package com.hxzy.boot.znglpt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.hxzy.boot.znglpt.entity.Station;

public interface StationDao extends JpaRepository<Station, Integer>,JpaSpecificationExecutor<Station>{
	@Query("from map_test m where m.name = ?1")
	public Station getStation(String name);
	
	@Query("select * from map_test")
	public List<Station> findAll();
}
