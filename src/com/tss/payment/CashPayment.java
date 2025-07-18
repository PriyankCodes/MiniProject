package com.tss.payment;

public class CashPayment implements IPayment {

	private double finalAmount;

	public CashPayment(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String pay() {
		return "Paid ₹" + finalAmount + " via Cash";
	}

}
