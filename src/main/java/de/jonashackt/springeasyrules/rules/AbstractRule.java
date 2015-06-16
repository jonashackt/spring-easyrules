package de.jonashackt.springeasyrules.rules;

import de.jonashackt.springeasyrules.PlausibilityStatus;

public class AbstractRule {

	private PlausibilityStatus status;
	private String message;
	
	public PlausibilityStatus getStatus() {
		return status;
	}
	public void setStatus(PlausibilityStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
