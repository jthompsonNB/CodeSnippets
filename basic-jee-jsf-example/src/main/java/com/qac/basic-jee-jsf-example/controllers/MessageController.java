package com.qac.example.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.qac.example.service.MessageService;

@Named("message")
@RequestScoped
public class MessageController {
	@Inject
	private MessageService messageService;
	private String message = "";
	
	public String getLastMessage() {
		return messageService.findLast();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String submitMessage(){
		if(!messageService.validateMessage(message))
			messageService.validateMessage("Please no rude words");
		return "index";
	}
}