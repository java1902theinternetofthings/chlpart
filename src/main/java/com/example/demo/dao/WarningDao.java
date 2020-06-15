package com.example.demo.dao;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Warning;

public interface WarningDao extends JpaRepository<Warning, Integer>,JpaSpecificationExecutor<Warning>{

	@Query("from Warning w where w.id = ?1")
	public Warning getWarningById(Integer id);
	
	@Query("from Warning w where w.classification = ?1 and "
			+ "w.townshipname = ?2 and w.villagename = ?3 and w.sitename = ?4 and w.effectiveness = ?5")
	public Warning searchWarningByClassification(String classification,
			String townshipname,String villagename,String sitename,
			String effectiveness);
	
//	@Query("from Warning w where w.townshipname = ?1")
//	public Warning searchWarningByTownshipname(String townshipname);
//	
//	@Query("from Warning w where w.villagename = ?1")
//	public Warning searchWarningByVillagename(String villagename);
//	
//	@Query("from Warning w where w.sitename = ?1")
//	public Warning searchWarningBySitename(String sitename);
//	
//	@Query("from Warning w where w.effectiveness = ?1")
//	public Warning searchWarningByEffectiveness(String effectiveness);
	
}
