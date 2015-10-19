package org.people.service;

/**
 * An exception that is thrown by classes wanting to trap unique constraint
 * violations. This is used to wrap Spring's DataIntegrityViolationException so
 * it's checked in the web layer.
 * 
 */
public class FamilyExistsException extends Exception {

	private static final long serialVersionUID = -3397927067870685272L;

	/**
	 * Constructor for FamilyExistsException.
	 * 
	 * @param message
	 */
	public FamilyExistsException(String message) {
		super(message);
	}
}
