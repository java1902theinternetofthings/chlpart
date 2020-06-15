package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Station;

public interface StationDao extends JpaRepository<Station, Integer>,JpaSpecificationExecutor<Station>{
	@Query(value="from T_Station t where t.name = ?1",nativeQuery = true)
	public Station getStation(String name);
	
	@Query(value="select * from T_Station",nativeQuery = true)
	public List<Station> findAll();
}
