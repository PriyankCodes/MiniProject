package com.tss.payment;

import com.tss.model.IPayment;

public class CardPayment implements IPayment {

	private String cardNumber;
	private String pin;
	private double finalAmount;

	public CardPayment(String cardNumber, String pin, double finalAmount) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.finalAmount = finalAmount;
	}

	public CardPayment() {

	}

	public String pay() {
		return "Paid ₹" + finalAmount + " via Card : " + cardNumber;
	}
}
