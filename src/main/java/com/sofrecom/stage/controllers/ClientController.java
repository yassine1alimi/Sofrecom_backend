package com.sofrecom.stage.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Claim;
import com.sofrecom.stage.models.Client;
import com.sofrecom.stage.repository.IClientRepo;
import com.sofrecom.stage.services.ClientServiceImpl;
import com.sofrecom.stage.services.ReportService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClientServiceImpl clientService;
	
	@Autowired
	private IClientRepo clientRepo;
	
	@Autowired
	private ReportService reportService ; 
	
	@Autowired  
	ServletContext context;
	
	
	
	
	 @GetMapping("/clients")
	    public List<Client> getAllClients() {
	        return clientRepo.findAll();
	    }

	
	 
	 
	 
//	 @GetMapping("/photoEmploye/{id}")
//	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
//		 
//		 Employe employe = employeRepo.findById(id).get();
//		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+employe.getPhoto()));
//		 	
//	 }
	 
	 
	 
	 

	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
		Optional<Client> client = clientService.findById(id);
		if (client.isPresent())
			return new ResponseEntity<Client>(client.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);

	}

	@PostMapping("/clients/create")
	public ResponseEntity<Client> create(@RequestBody Client employe) {
		try {
			clientService.saveClient(employe);
			return new ResponseEntity<Client>(employe, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Client>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	

	@DeleteMapping("/deleteClient/{id}")
	public ResponseEntity<Client> deleteEmploye(@PathVariable("id") Long id) {
		Optional<Client> emp = clientService.findById(id);
		if (emp.isPresent()) {
			clientService.deleteClient(emp.get());
			return new ResponseEntity<Client>(emp.get(), HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<Client>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	/*@DeleteMapping("/clients/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
          {
        Optional<Employe> employee = employeRepo.findById(employeeId);

        employeRepo.deleteById(employeeId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }*/
	
	@DeleteMapping("/clients/{id}")
	  void deleteClient(@PathVariable Long id) {
		clientRepo.deleteById(id);
	  }

	
	
	@GetMapping("/clients/count")
	Long countEmploye() {
		return clientService.countEmploye();
	}
	
	@GetMapping("/clients/claims")
	List<Claim> getClaimByclientId (Long id) {
		return clientService.getClaimeByclientId(id);
	}
}
