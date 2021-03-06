package com.sofrecom.stage.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Demande implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idDemande;
	private String typeDemande;
	private Date debutDemande;
	private Date finDemande;
	private Date createdAt;
	private String description;
	//private int duree ; 
	//private int soldeConge ; 
	private String statusOfDemand="Waiting";
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "idUser")
	private UserInformation user;
	
	

}
