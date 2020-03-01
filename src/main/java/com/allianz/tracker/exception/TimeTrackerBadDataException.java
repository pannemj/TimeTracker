package com.allianz.tracker.exception;

public class TimeTrackerBadDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5463015267933355176L;
	
	private final String userMessage;

	public TimeTrackerBadDataException(String userMessage) {
		super(userMessage);
		this.userMessage = userMessage;
	}

	public String getUserMessage() {
		return this.userMessage;
	}
}
