package com.samyuktha.exception;

/**
 * Signals a general, unsupported value assignment error.
 * Error details may be specified when calling the constructor, as usual.
 */
public class IllegalAssignmentException extends Exception {
	
	private static final long serialVersionUID = 8425019985238095443L;
	
	/**
     * Constructs a new {@code IllegalAssignmentException} with its stack trace
     * and default message filled in.
     */
	public IllegalAssignmentException() {
		super ("Value cannot be assigned to the variable");
	}
	
	/**
     * Constructs a new {@code IllegalAssignmentException} with its stack trace
     * and message filled in.
     *
     * @param message
     *            the detail message for this exception.
     */
	public IllegalAssignmentException(String message) {
		super (message);
	}
}
