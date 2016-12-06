package com.qac.example.managers.offline;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import com.qac.example.managers.MessageManager;
import com.qac.example.util.TestData;

@Stateless
@Default
public class MessageManagerOffline implements MessageManager {
	@Inject
	private TestData testData;
	
	@Override
	public void save(String message) {
		testData.getMessages().add(message);
	}

	@Override
	public String findLast() {
		try {
			return testData.getMessages().get(testData.getMessages().size()-1);
		} catch (Exception e) {
			return null;
		}
	}
}
