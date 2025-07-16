package com.tss.exception;

public class EmptyCartException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Cart is empty. Please add items before checkout.";
	}
}
