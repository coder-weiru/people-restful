package org.people.service;

/**
 * An exception that is thrown by classes wanting to trap unique constraint
 * violations. This is used to wrap Spring's DataIntegrityViolationException so
 * it's checked in the web layer.
 * 
 */
public class PersonExistsException extends Exception {

	private static final long serialVersionUID = -5020144681103095975L;

	/**
	 * Constructor for PersonExistsException.
	 * 
	 * @param message
	 */
	public PersonExistsException(String message) {
		super(message);
	}
}
