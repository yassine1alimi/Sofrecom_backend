package com.sofrecom.stage.services;



import java.util.Date;
import java.util.List;

import com.sofrecom.stage.dto.ReclamationDto;
import com.sofrecom.stage.models.Employe;
import com.sofrecom.stage.models.ReclamationClient;
import com.sofrecom.stage.models.Timesheet;
import com.sofrecom.stage.models.TimesheetPK;
public interface ITimesheetService {
	public Long ajouterReclamation(ReclamationClient reclamation);
	public ReclamationClient getReclamationById(Long idReclamation);
	public int affecterReclamationAGroupe(Long idReclamation, int groupeId);
	public void ajouterTimesheet(Long idReclamation, Long idEmploye, Date dateDebut, Date dateFin);
	public void addTimesheet(Timesheet timesheet);
	
	public int validerTimesheet(Long idReclamation, Long idEmploye, Date dateDebut, Date dateFin, Long validateurId);
	public List<ReclamationClient> findAllReclamationByEmployeJPQL(Long idEmploye);
	public List<Employe> getAllEmployeByReclamation(Long reclamationId);


	
	//public void ajouterTimesheet1(Long idClaim, Long idUser, Date dateDebut, Date dateFin);

}
