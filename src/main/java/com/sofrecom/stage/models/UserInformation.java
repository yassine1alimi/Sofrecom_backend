package com.sofrecom.stage.models;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(	name = "users", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "username"),
	@UniqueConstraint(columnNames = "email") 
})
public class UserInformation implements Serializable {

	
	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	
	
	private String username;

	private String prenom;
	
	private String nom;
	
	private LocalDate dateOfBirth;
	
	private String phone;
	
	private String email;
	private String cin;
	private String adresse ; 
	//private String ville; 
	private String photo ="avatar.jpg" ; 
	private String departement;
	private String password;
	private String repassword;
	private String fonction;
private String groupe_name;
private LocalDate dateEntree;
private LocalDate dateSortie;
private String salary;

private int dureeConges = 30;
private int joursConges;
private int soldeConges = 30;
	private int archived = 1 ; 
	
	
	//private boolean archived;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Demande> demandes;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
	private List<Claim> claims;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userReclamation")
	private List<ReclamationClient> Reclamations;
	
	

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	public UserInformation (String username, String email,LocalDate dateOfBirth,String phone, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
	}


	public UserInformation(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	/*@JsonIgnore
	//@JsonBackReference
	@OneToMany(mappedBy="user2")
	private List<Timesheet> timesheets1;*/
	







	@ElementCollection
	Set<String> userPreferences;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}


	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCin() {
		return cin;
	}


	public void setCin(String cin) {
		this.cin = cin;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getDepartement() {
		return departement;
	}


	public void setDepartement(String departement) {
		this.departement = departement;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRepassword() {
		return repassword;
	}


	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}


	public String getFonction() {
		return fonction;
	}


	public void setFonction(String fonction) {
		this.fonction = fonction;
	}


	public String getGroupe_name() {
		return groupe_name;
	}


	public void setGroupe_name(String groupe_name) {
		this.groupe_name = groupe_name;
	}


	public LocalDate getDateEntree() {
		return dateEntree;
	}


	public void setDateEntree(LocalDate dateEntree) {
		this.dateEntree = dateEntree;
	}


	public LocalDate getDateSortie() {
		return dateSortie;
	}


	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}


	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}


	public int getArchived() {
		return archived;
	}


	public void setArchived(int archived) {
		this.archived = archived;
	}


	public List<Demande> getDemandes() {
		return demandes;
	}


	public void setDemandes(List<Demande> demandes) {
		this.demandes = demandes;
	}


	public List<Claim> getClaims() {
		return claims;
	}


	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}


	public List<ReclamationClient> getReclamations() {
		return Reclamations;
	}


	public void setReclamations(List<ReclamationClient> reclamations) {
		Reclamations = reclamations;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}








	public Set<String> getUserPreferences() {
		return userPreferences;
	}


	public void setUserPreferences(Set<String> userPreferences) {
		this.userPreferences = userPreferences;
	}
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user4")
	private List<Conge> conges;
}
