package com.samyuktha.io;

/**
 * Enum to define the status of the response.
 */
public enum Status {
	
	OK("Okay"),
	EXCEED("Sorry, we can't handle your party."),
	SPLIT("Call to split party."),
	INVALID("Invalid request. Either name and/or requested seats have invalid entry.");
	
	private String description = null;
	
	private Status(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}