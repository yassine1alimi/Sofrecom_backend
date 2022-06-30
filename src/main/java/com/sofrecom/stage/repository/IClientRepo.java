package com.sofrecom.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Client;
@Repository
public interface IClientRepo extends JpaRepository<Client, Long> {
	@Query("SELECT count(*) FROM Client")
    public int countclient();
	
	 @Query("SELECT username FROM Client")
	    public List<String> userNames();
}
