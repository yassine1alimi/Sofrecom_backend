package com.sofrecom.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.dto.CountStatus;
import com.sofrecom.stage.dto.CountTypeClaim;
import com.sofrecom.stage.models.Claim;
import com.sofrecom.stage.models.Demande;
@Repository
public interface IClaimRepo extends JpaRepository<Claim, Long> {
	@Query("select c from Claim c where c.statusOfDemand like 'Waiting'")
	public List<Claim> getClaimByStatus();
	
	
	
	@Query(value ="select new com.sofrecom.stage.dto.CountTypeClaim(COUNT(*)/(Select COUNT(*) from Claim)*100,typeClaim)from Claim GROUP BY typeClaim")
    public List<CountTypeClaim> getPercentageGroupByType();
	
	@Query(value ="select new com.sofrecom.stage.dto.CountStatus(COUNT(*)/(Select COUNT(*) from Claim)*100,statusOfDemand)from Claim GROUP BY statusOfDemand")
    public List<CountStatus> getPercentageGroupByStatus();
}
