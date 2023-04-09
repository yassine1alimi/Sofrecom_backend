package com.sofrecom.stage.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.models.Message;
import com.sofrecom.stage.models.MessageModel;
import com.sofrecom.stage.models.Modelx;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IUtilidateurRepo;
import com.sofrecom.stage.repository.MessageRepository;





@Service
public class MessageService {
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	IUtilidateurRepo clientRepository;
	List<UserInformation> La = new ArrayList<UserInformation>();
	List<Message> Lb = new ArrayList<Message>();
	List<MessageModel> Lc = new ArrayList<MessageModel>();
	List<Integer> Ld = new ArrayList<Integer>();
	Calendar calendar = Calendar.getInstance();
	
   public String SendMessageService(MessageModel m) {
	  boolean test = false;
	  List<UserInformation> La = new ArrayList<UserInformation>();
	  La =  clientRepository.findAll();
	  Message message = new Message();
	  for(UserInformation client : La) {
		  if(m.getUser_name_reciver().equals(client.getUsername())) {
			  message.setId_reciver(client.getIdUser());
			 
			  test = true;
		  }
		  if(m.getUser_name_sender().equals(client.getUsername())) {
			 
			  message.setId_sender(client.getIdUser());
			  test = true;
		  }
		  }
		  if(test) {
			 
			  message.setDate_message(calendar.getTime());
			  message.setUser_name_reciver(m.getUser_name_reciver());
			  message.setUser_name_sender(m.getUser_name_sender());
			  message.setMessage(m.getMessage());
			 
			  messageRepository.save(message);
			  
		  }
		  
	  
	  
	  return "message sent";
	  
	   
   }
   public List<MessageModel> showChatService(Modelx param){
	   List<MessageModel> Lchat  = new ArrayList<MessageModel>();
	   List<MessageModel> Lres  = new ArrayList<MessageModel>();
	   Lchat =  showDisscussionService( param.getUserName1());
	   
	   if(param.getUserName1().equals(param.getUserName2())) {
		   for(MessageModel obj : Lchat ) {
			   
				
			   if((param.getUserName1().equals(obj.getUser_name_reciver()))&&(param.getUserName1().equals(obj.getUser_name_sender()))) {
				  
				   Lres.add(obj);
			   }
		   }
	   }
	   else {
		   for(MessageModel obj : Lchat ) {
			   
				
			   if((param.getUserName2().equals(obj.getUser_name_reciver()))||(param.getUserName2().equals(obj.getUser_name_sender()))) {
				  
				   Lres.add(obj);
			   }
		   }
	   }
	   return Lres ;
   }
   public boolean notExistInt (int i , List<Integer> L) {
	   boolean result = true;
	   for(int j : L) {
		   if (i==j) {
			   result = false;
		   }
	   }
	   return result ; 
   }
   public boolean notExistString (String i , List<String> L) {
	   boolean result = true;
	   for(String j : L) {
		   if (i.equals(j)) {
			   result = false;
		   }
	   }
	   return result ; 
   }
   
   
   public List<MessageModel> showDisscussionService(String userName){
	   MessageModel model = new MessageModel();
	   List<Message> Lb = new ArrayList<Message>();
	   List<MessageModel> Lc = new ArrayList<MessageModel>();
	   List<String> Ld = new ArrayList<String>();
	   Lb = messageRepository.showMessageRepository();
	   boolean x = false;
	   boolean y = false;
	   boolean t1 = false;
	   boolean t2 = false;
	   String userNameOther = "";
	   for(Message message : Lb) {
		   
		   x= message.getUser_name_reciver().equals(userName);
		   y= message.getUser_name_sender().equals(userName);
		  
		   if(x || y) {
			   if(x) {
				  
				   userNameOther = message.getUser_name_sender();
				   
			   }
			   else if(y) {
				  
				   userNameOther = message.getUser_name_reciver();
				   
			   }
			  if(notExistString(userNameOther,Ld)) {
				  Ld.add(userNameOther);
				  for(Message message1 : Lb) {
					   t1 = userName.equals(message1.getUser_name_reciver()) && userNameOther.equals(message1.getUser_name_sender());
					   t2 = userName.equals(message1.getUser_name_sender()) && userNameOther.equals(message1.getUser_name_reciver());
					   
					   if(t1 || t2 ) {
						   model = new MessageModel();
						 
						   model.setDate_message(message1.getDate_message()); 
						   model.setUser_name_reciver(message1.getUser_name_reciver());
						   model.setUser_name_sender(message1.getUser_name_sender());
						   model.setMessage(message1.getMessage());
						 
						   Lc.add(model);
						   
						  
					   }
				   }
			  }
		   }
	   }
	   return Lc;
   }
   
   
   
   
   
  
   
   
}
