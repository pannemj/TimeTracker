package com.allianz.tracker.exception;

public class TimeTrackerAppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5463015267933355176L;
	
	private final String userMessage;

	public TimeTrackerAppException(String userMessage) {
		super(userMessage);
		this.userMessage = userMessage;
	}

	public String getUserMessage() {
		return this.userMessage;
	}
}
