package com.sofrecom.stage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Contact;
import com.sofrecom.stage.models.Faq;
import com.sofrecom.stage.repository.ContactRepository;



@CrossOrigin
@RestController
public class ContactController {
	@Autowired
	private ContactRepository contactRepo ; 
	
	@PostMapping("/createContact")
    public Contact createContact( @RequestBody Contact contact) {
		System.out.println("hello"+contact.getSubject());
		return contactRepo.save(contact);
		
	}
	
	
	
	
	@GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }
	
	@DeleteMapping("/contacts/{id}")
	void deleteContact(@PathVariable Long id) {
		contactRepo.deleteById(id);
	  }
	
	
	
	@PutMapping("/annoncecontact")
	public Contact editContact(@RequestBody Contact contact) {
		return contactRepo.save(contact);
	}
	
}
