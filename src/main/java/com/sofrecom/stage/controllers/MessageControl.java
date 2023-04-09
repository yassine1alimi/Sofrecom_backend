package com.sofrecom.stage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.MessageModel;
import com.sofrecom.stage.models.Modelx;
import com.sofrecom.stage.services.MessageService;



@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/auth")
public class MessageControl {
	@Autowired
	private MessageService messageService ;
	
	@PostMapping("/showChat")
	public List<MessageModel> showChatService(@RequestBody Modelx param){
		return messageService.showChatService(param);
	}
    @PostMapping("/sendMessage")
	public String SendMessage(@RequestBody MessageModel m) {
		return messageService.SendMessageService(m);
	}
    @GetMapping("/showMessage/{userName}")
    public List<MessageModel> showDisscussion(@PathVariable("userName") String userName){
    	return messageService.showDisscussionService(userName);
    }
    
    @MessageMapping("/socket/someoneJoined")
	@SendTo("/socket/someoneJoined")
	public String someoneJoined(String message) {
		return message;
	}
}
