package com.sofrecom.stage.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sofrecom.stage.models.Claim;
import com.sofrecom.stage.models.Client;
import com.sofrecom.stage.repository.IClientRepo;



@Service
public class ClientServiceImpl {
	@Autowired
	private IClientRepo clientRepo;
	
	
	public List<Client> getAllClients() {
		return clientRepo.findAll();
	}

	
	public boolean saveClient(Client employe) {

		if (clientRepo.save(employe) != null)
			return true;
		else
			return false;
	}

	
	public void deleteClient(Client employe) {
		if (employe != null)
			clientRepo.delete(employe);

	}

	
	public Client updateClient(Client employe) {
		return clientRepo.save(employe);
	}

	
	public Long countEmploye() {

		return clientRepo.count();

	}

	
	public Optional<Client> findById(Long id) {

		return clientRepo.findById(id);

	}
	
	public List<Claim> getClaimeByclientId (Long id){
		
		Client client = new Client();
		if (client.getClaims()!= null)
			return client.getClaims();
		else 
			return null ;
		
	}

	
	
	
	
	
	
	
	
	
	
}