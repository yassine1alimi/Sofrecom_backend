package com.sofrecom.stage.models;
import java.io.Serializable;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Affectation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTimesheet; 
	//Choisir le TemporalType selon le besoin metier
		@Temporal(TemporalType.DATE)
		private Date dateDebut;
		@Temporal(TemporalType.DATE)
		private Date dateFin;
		
		
		
		
		@ManyToOne
	    @JoinColumn(name = "idClaimAff", insertable=false, updatable=false)
		private Claim claimAffectation;
	
		//idEmploye est a la fois primary key et foreign key
		
		@ManyToOne
	    @JoinColumn(name = "idEmployeAff", insertable=false, updatable=false)
		private Employe employeAffectation;
}
