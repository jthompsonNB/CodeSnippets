package com.qac.example.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.qac.example.managers.MessageManager;

@Stateless
public class MessageService {
	@Inject
	private MessageManager messageManager;
	
	public boolean validateMessage(String message) {
		if(message.contains("boobies"))
			return false;
		messageManager.save(message);
		return true;
	}
	
	public String findLast() {
		String message = messageManager.findLast();
		if (message == null)
			message = "No Message Found";
		return message;
	}
}