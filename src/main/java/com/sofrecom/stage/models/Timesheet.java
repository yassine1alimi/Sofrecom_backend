package com.sofrecom.stage.models;

import java.io.Serializable;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ocpsoft.rewrite.config.True;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "timesheet")
public class Timesheet implements Serializable{
	/*private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTimesheet; 
	//Choisir le TemporalType selon le besoin metier
		@Temporal(TemporalType.DATE)
		private Date dateDebut;
		@Temporal(TemporalType.DATE)
		private Date dateFin;

		
		
		//idEmploye est a la fois primary key et foreign key
		
		@ManyToOne
	    @JoinColumn(name = "idEmploye", insertable=false, updatable=false)
		private Employe employe;
		@ManyToOne
	    @JoinColumn(name = "idClaim", insertable=false, updatable=false)
		private Claim claim;*/
	private static final long serialVersionUID = 3876346912862238239L;

	private static final boolean True = false;

	@EmbeddedId
	private TimesheetPK timesheetPK;
	
	//idReclamation est a la fois primary key et foreign key
	/*@ManyToOne
    @JoinColumn(name = "idReclamation")
	private ReclamationClient reclamationClient;
	*/
	/*
	@ManyToOne
    @JoinColumn(name = "idReclamation", insertable=false, updatable=false, nullable=True)
	private ReclamationClient reclamationClient;*/
	
	
	/*@ManyToOne
    @JoinColumn(name = "idClaim", insertable=false, updatable=false)
	private Claim claim;*/
	
	
	
	
	
	
	//idEmploye est a la fois primary key et foreign key
	
	@ManyToOne
    @JoinColumn(name = "idEmploye", insertable=false, updatable=false)
	private Employe employe;
	
	
	
	private boolean isValide;

	

	public boolean isValide() {
		return isValide;
	}

	public void setValide(boolean isValide) {
		this.isValide = isValide;
	}

	public TimesheetPK getTimesheetPK() {
		return timesheetPK;
	}

	public void setTimesheetPK(TimesheetPK timesheetPK) {
		this.timesheetPK = timesheetPK;
	}

	/*public ReclamationClient getReclamationClient() {
		return reclamationClient;
	}

	public void setReclamationClient(ReclamationClient reclamationClient) {
		this.reclamationClient = reclamationClient;
	}*/

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	
	
	
	
	/*@ManyToOne
    @JoinColumn(name = "idUser", insertable=false, updatable=false)
	private UserInformation user2;*/
	
	
}
