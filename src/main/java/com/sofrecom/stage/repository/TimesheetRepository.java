package com.sofrecom.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;

import com.sofrecom.stage.dto.ReclamationDto;
import com.sofrecom.stage.models.Claim;
import com.sofrecom.stage.models.Employe;

import com.sofrecom.stage.models.ReclamationClient;
import com.sofrecom.stage.models.Timesheet;
import com.sofrecom.stage.models.TimesheetPK;


@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {

	
	/*
	 	@Query("select DISTINCT e from User e "
				+ "join e.timesheets t "
				+ "join t.reclamation m "
				+ "where m.id=:misId")
	  
	 */
	
	//@Query("select DISTINCT m from Timesheet t, ReclamationClient m  where m.idReclamation=t.idReclamation AND t.idEmploye=:idEmploye")
	@Query(value="SELECT reclamation_client.* FROM `reclamation_client` AS r ,`timesheet` as t WHERE r.id_reclamation=t.id_reclamation AND t.id_employe=:idEmploye",nativeQuery=true)
	public List<ReclamationClient> findAllReclamationByUserJPQL(@Param("idEmploye") Long idEmploye);
	
	
/*	
	 @Query("select DISTINCT m from ReclamationClient m join m.timesheets t join t.employe e where e.id=:idEmploye")
	public List<ReclamationClient> findAllReclamationByUserJPQL(@Param("idEmploye") Long idEmploye);
	
	@Query("select DISTINCT e from Employe e "
				+ "join e.timesheets t "
				+ "join t.reclamationClient m "
				+ "where m.idReclamation=:id")
	public List<Employe> getAllUserByReclamation(@Param("id")Long idReclamation);
	
	
	@Query("Select t from Timesheet t "
				+ "where t.reclamationClient=:rec and "
				+ "t.employe=:emp and "
				+ "t.timesheetPK.dateDebut>=:dateD and "
				+ "t.timesheetPK.dateFin<=:dateF")
	public List<Timesheet> getTimesheetsByReclamationAndDate(@Param("emp")Employe employe, @Param("rec")ReclamationClient reclamation, @Param("dateD")Date dateDebut,@Param("dateF")Date dateFin);

	
	*/
	
	  public Timesheet findBytimesheetPK(TimesheetPK timesheetPK);

	  
	  


	  
	  
	  
	  
	  
	/*  @Query("select DISTINCT m from Claim m join m.timesheets t join t.employe e where e.id=:idUser")
		public List<ReclamationClient> findAllClaimByUserJPQL(@Param("idUser") Long userId);
		@Query("select DISTINCT e from Employe e "
					+ "join e.timesheets t "
					+ "join t.claim m "
					+ "where m.idClaim=:id")
		public List<Employe> getAllUserByClaim(@Param("id")Long idClaim);
		
		
		@Query("Select t from Timesheet t "
					+ "where t.claim=:rec and "
					+ "t.employe=:emp and "
					+ "t.timesheetPK.dateDebut>=:dateD and "
					+ "t.timesheetPK.dateFin<=:dateF")
		public List<Timesheet> getTimesheetsByClaimAndDate(@Param("emp")Employe employe, @Param("rec")Claim claim, @Param("dateD")Date dateDebut,@Param("dateF")Date dateFin);

	  */
	
	  
}