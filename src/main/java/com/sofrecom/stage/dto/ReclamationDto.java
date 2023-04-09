package com.sofrecom.stage.dto;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ReclamationDto {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idReclamation;
	private String username;

	

	/*private String prenom;
	private String nom;
	private Date dateReclamation;
	private String phone;
	private String email;

	 
	private String typeClaim;
	private String description;
	private String statusOfDemand="NOT_YET_TREATED";
	private String pj1;
	private String pj2;*/
}
