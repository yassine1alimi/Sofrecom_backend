package com.sofrecom.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Affectation;
import com.sofrecom.stage.models.ReclamationClient;
@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {

	
	/*@Query("select DISTINCT m from Claim m join m.affectations t join t.employeAffectation e where e.id=:idEmploye")
	public List<ReclamationClient> findAllReclamationByUserJPQL(@Param("idEmploye") Long idEmploye);
	*/
	
}
