package com.tss.exception;

public class NoMenuAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "No menus available. Please add a menu first.";
	}
}