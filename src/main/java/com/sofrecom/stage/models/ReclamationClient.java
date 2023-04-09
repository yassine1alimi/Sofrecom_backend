package com.sofrecom.stage.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reclamation_client")
public class ReclamationClient {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idReclamation;
	private String username;

	

	private String prenom;
	private String nom;
	private Date dateReclamation;
	private String phone;
	private String nom_societe;
	private String email;
	private String cin;
	private String adresse ; 
	private Date dateOfBirth;

	private String ville; 
	//private String nationality; 
	private String typeClaim;
	private String description;
	private String statusOfDemand="NOT_YET_TREATED";
	private String pj1;
	private String pj2;
	private Boolean archived ; 
	private String name_groupe;
	
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private Employe employe;
    
	@ManyToOne
	private Groupe groupe;
	
	/*
	@OneToMany(mappedBy="reclamationClient")
	private  List<Timesheet> timesheets;
	*/
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
	private UserInformation userReclamation;
	
}
