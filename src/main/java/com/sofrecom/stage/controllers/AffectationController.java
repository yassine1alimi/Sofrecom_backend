package com.sofrecom.stage.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Affectation;
import com.sofrecom.stage.models.Claim;
import com.sofrecom.stage.models.Employe;
import com.sofrecom.stage.models.ReclamationClient;
import com.sofrecom.stage.models.Timesheet;
import com.sofrecom.stage.repository.AffectationRepository;
import com.sofrecom.stage.repository.IClaimRepo;
import com.sofrecom.stage.repository.IEmployeRepo;
import com.sofrecom.stage.repository.IReclamationClientRepo;
import com.sofrecom.stage.services.AffectationService;
import com.sofrecom.stage.services.EmployeServiceImpl;
import com.sofrecom.stage.services.ITimesheetService;
@CrossOrigin
@RestController
public class AffectationController {
	@Autowired
	EmployeServiceImpl usereservice;
	
	@Autowired
	AffectationService itimesheetservice;
	@Autowired
	private IEmployeRepo userRepo ;
	@Autowired IClaimRepo claimRepo;
	@Autowired
	AffectationRepository afectRepository;
	/*
	
	@PostMapping("/affectation")
	@ResponseBody
	public void addTimesheet(@Valid @RequestBody Affectation timesheet,@RequestParam Long idEmployeAff,@RequestParam Long idClaimAff) {
		Employe user = userRepo.findById(idEmployeAff).get();
    timesheet.setEmployeAffectation(user);
	Claim reclamation = claimRepo.findById(idClaimAff).get();
        timesheet.setClaimAffectation(reclamation);
        afectRepository.save(timesheet);
	}*/
	
}
