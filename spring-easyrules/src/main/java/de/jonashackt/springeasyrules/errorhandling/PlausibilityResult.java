package de.jonashackt.springeasyrules.errorhandling;

import java.util.ArrayList;
import java.util.List;

public class PlausibilityResult {

	private PlausibilityStatus status;
	private final List<String> messages = new ArrayList<String>();
	
	public PlausibilityStatus getStatus() {
		return status;
	}
	
	public void setStatus(PlausibilityStatus status) {
		this.status = status;
	}	
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
	
}
