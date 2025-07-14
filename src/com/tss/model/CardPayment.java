package com.tss.model;

public class CardPayment implements IPayment {

	private String cardNumber;
	private String pin;
	private double finalAmount;

	public CardPayment(String cardNumber, String pin, double finalAmount) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.finalAmount = finalAmount;
	}

	public void pay() {
		System.out.println("Paid ₹" + finalAmount + " via Card : " + cardNumber);
	}
}
