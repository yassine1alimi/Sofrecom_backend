package com.sofrecom.stage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.dto.CountStatus;
import com.sofrecom.stage.dto.CountTypeClaim;
import com.sofrecom.stage.models.Claim;
import com.sofrecom.stage.models.Demande;
import com.sofrecom.stage.models.Groupe;
import com.sofrecom.stage.models.Meeting;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IClaimRepo;
import com.sofrecom.stage.repository.IDemandeRepo;
import com.sofrecom.stage.repository.IUtilidateurRepo;

@Service
public class ClaimServiceImpl {
	private int d=0;
	@Autowired
	private IClaimRepo claimRepo ; 
	
	@Autowired
	private IUtilidateurRepo userRepo ;
	 
	
	public List<Claim> getAllClaims (Demande demande){
		return claimRepo.findAll(); 
	}
	
	
	public Optional<Claim> findClaimById (Long id) {
		return claimRepo.findById(id);
	}
	public Claim createClaim (Claim claim) {
		claim.setStatusOfDemand("not_yet_treated");
		return claimRepo.save(claim);
	}
	public Claim updateClaim (Claim claim) {
		return claimRepo.save(claim);
		
	}
	
	
	public List<Claim> getAllClaims() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Claim CreateClaim(Claim claim) {
		// TODO Auto-generated method stub
		return null;
	}
	public void deleteClaim(Claim claim) {
		claimRepo.delete(claim);
		
	}
	
	
	
public void accepterClaim(Long id) {
		
	Claim claim = claimRepo.findById(id).get();
		 	UserInformation user = claim.getUser1();
		 	
		 	claim.setStatusOfDemand("Accepted");
		 	claimRepo.save(claim);
			
			userRepo.save(user);
		 	
			
		}
	
	
	public boolean refuserClaim (Long id) {
		
		Optional<Claim> claim = claimRepo.findById(id);
		if (claim.isPresent()) {
			claim.get().setStatusOfDemand("Refused");
			claimRepo.save(claim.get());
		return true ;
		}
		return false ;
	}
	
	
	
	
	public List<CountTypeClaim> getPercentageGroupByType(){
		return claimRepo.getPercentageGroupByType();
	}
	
	public List<CountStatus> getPercentageGroupByStatus(){
		return claimRepo.getPercentageGroupByStatus();
	}
	
	
	
	
/*	public List<Claim> getClaimByGroupe() {
		
		return (List<Claim>) claimRepo.getClaimByGroupe();
	}*/
}
