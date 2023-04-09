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

import com.sofrecom.stage.models.Faq;
import com.sofrecom.stage.models.NoteInterne;
import com.sofrecom.stage.repository.FaqRepo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FaqController {
	@Autowired
	private FaqRepo faqRepo ; 
	
	@PostMapping("/createFaq")
    public Faq createCandidate( @RequestBody Faq faq) {
		System.out.println("hello"+faq.getTitle());
		return faqRepo.save(faq);
		
	}
	
	@GetMapping("/faqs")
    public List<Faq> getAllFaqs() {
        return faqRepo.findAll();
    }
	
	@DeleteMapping("/faqs/{id}")
	void deleteFaq(@PathVariable Long id) {
		faqRepo.deleteById(id);
	  }
	
	
	
	@PutMapping("/annoncefaq")
	public Faq editFaq(@RequestBody Faq faq) {
		return faqRepo.save(faq);
	}

}
