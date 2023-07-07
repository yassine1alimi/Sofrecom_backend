package com.sofrecom.stage.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Mail;
import com.sofrecom.stage.models.ReclamationClient;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IReclamationClientRepo;
import com.sofrecom.stage.repository.IUtilidateurRepo;
import com.sofrecom.stage.services.EmailService;
import com.sofrecom.stage.services.ReclamationClientService;
import com.sofrecom.stage.utils.ServiceManager;



@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class EmailController {

		@Autowired
	    public EmailService emailService;
		@Autowired
		public ReclamationClientService recservice;
		
		@Autowired
		private IUtilidateurRepo userRepo ;
		@Autowired
		private ServiceManager serviceManager;
		@Autowired
		public IReclamationClientRepo reclamationClientRepo ;
		
		@GetMapping("/sendConfirmMessage/{id}")
		public String sendConfirmMessage(@PathVariable("id") Long id,SecurityContextHolderAwareRequestWrapper request) {
			 Principal connectedUser = request.getUserPrincipal();
			 String currentusername = connectedUser.getName();
			Optional<ReclamationClient> emp = reclamationClientRepo.findById(id);
			 Optional<UserInformation> currentuser = userRepo.findByUsername(currentusername);
			/*this.serviceManager.restTemplateAssignetask("http://localhost:9090/process/assignetask",currentuser.get().getEmail());

			this.serviceManager.restTemplateCompleteTask("http://localhost:9090/process/completetask");*/
			Optional<ReclamationClient> reclamationClient = reclamationClientRepo.findById(id);
			ReclamationClient rec = reclamationClient.get();
			recservice.accepterReclamationClient(rec.getIdReclamation());
			Mail mail = new Mail();
			mail.setFrom("yassine.mnasria@esprit.tn");
			mail.setTo(reclamationClient.get().getEmail());
			mail.setSubject("Appreciation for Lodging Complaints to Sofrecom");
			mail.setContent("Dear "+reclamationClient.get().getPrenom()+","+"\r\n" + 
					"\r\n" + 
					"Thank you for taking the time to submit your complaints to Sofrecom. " +
					"We greatly value your feedback as it helps us improve our services and ensure the highest level of customer satisfaction..\r\n" +
					 
					"\r\n" + 
					"Rest assured, your complaints have been duly received, and we have already initiated an investigation into the matters you raised. " +
					"Our team is diligently reviewing the details and working towards finding a prompt and satisfactory resolution.\r\n" +
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"best regards\r\n" +
					"Sofrecom Customer Support Team");
			emailService.sendSimpleMessage(mail);
			
			return "message sent successffully";
			
		}
		
		@GetMapping("/sendDenyMessage/{id}")
		public String sendDenyMessage(@PathVariable("id") Long id) {
			
			Optional<ReclamationClient> reclamationClient = reclamationClientRepo.findById(id);
			Mail mail = new Mail();
			mail.setFrom("yassine.mnasria@esprit.tn");
			mail.setTo(reclamationClient.get().getEmail());
			mail.setSubject("Regretful Response to Your Application at Sofrecom");
			mail.setContent("Dear "+reclamationClient.get().getPrenom()+","+"\r\n" + 
					"\r\n" + 
					"Thank you for your recent application to Sofrecom. " +
					"We appreciate your interest in joining our team and for taking the time to submit your application.\n" +
					"After careful consideration and review of your qualifications and experience, we regret to inform you that we will not be able to proceed further with your application at this time. \r\n" +
					"\r\n" +
					"\r\n" +
					"\r\n" +
					"best regards\r\n" +
					"Sofrecom Customer Support Team");
			emailService.sendSimpleMessage(mail);
			return "message sent successffully";
			
		}
		
		
}