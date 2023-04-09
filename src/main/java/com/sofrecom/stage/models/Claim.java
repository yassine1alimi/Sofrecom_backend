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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Claim implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idClaim;
	private Date createdAt;
	private String typeClaim;
	private String description;
	private String statusOfDemand="Waiting";
	private String fileClaim;
    private String priorite;
    private String groupe_name;
	

	
	
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "idUser")
	private UserInformation user1;
	
	
	/*@JsonIgnore
	@OneToMany(mappedBy="claim")
	private  List<Timesheet> timesheets;*/
	
	
	@JsonIgnore
	@OneToMany(mappedBy="claimAffectation")
	private  List<Affectation> affectations;
	


}
