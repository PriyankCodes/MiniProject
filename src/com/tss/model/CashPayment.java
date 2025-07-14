package com.tss.model;

public class CashPayment implements IPayment {

	private double finalAmount;

	public CashPayment(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public void pay() {
		System.out.println("Paid ₹" + finalAmount + " via Cash ");
	}

}
