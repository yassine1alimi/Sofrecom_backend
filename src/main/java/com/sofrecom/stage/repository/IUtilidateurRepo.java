package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Departement;
import com.sofrecom.stage.models.Employe;
import com.sofrecom.stage.models.Role;
import com.sofrecom.stage.models.UserInformation;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUtilidateurRepo extends JpaRepository <UserInformation, Long> {
	
Optional<UserInformation> findByUsername (String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	Optional<UserInformation> findByEmail(String email);
	
	@Query("select u from UserInformation u where u.archived = 1")
	List<UserInformation> getEmployes ();
	
	@Query("select u from UserInformation u where u.archived = 2")
	List<UserInformation> getArchivedEmployes ();
   
	@Query("select emp from UserInformation emp where emp.departement =:departement")
	List<UserInformation> getAllEmployesByDepartement(@Param("departement") String departement);
	
	@Query("SELECT u FROM UserInformation as u ,Role as ur WHERE u.idUser=ur.id AND ur.id=:role ")
	List<UserInformation> findUserByRole(@Param("role") int role);
	
	@Query("SELECT u FROM UserInformation as u ,Role as ur WHERE ur.name=:role ")
	List<UserInformation> findUserByRole9(@Param("role") Role role);

	
	
	/*@Query(value = "SELECT * FROM users u where u.role_id=:2", nativeQuery = true)
	List<UserInformation> findUserByRole1();*/

	//List<UserInformation> getEmployeList(String roleName);
	 @Query("SELECT user FROM UserInformation user LEFT JOIN user.roles role WHERE role.id = ?1")
	    List<UserInformation> findUserByRole1(int role);
	
	
}
	
	





 
