package com.qac.example.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class TestData {
	private List<String> messages;
	
	@PostConstruct
	public void setupData() {
		messages = new ArrayList<String>();
		messages.add("Hello From Test Data");
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}