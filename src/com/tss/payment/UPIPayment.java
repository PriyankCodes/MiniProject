package com.tss.payment;

public class UPIPayment implements IPayment {

	private String upiId, pin;
	private double finalAmount;

	public UPIPayment(String upiId, String pin, double finalAmount) {
		super();
		this.upiId = upiId;
		this.pin = pin;
		this.finalAmount = finalAmount;
	}

	public String pay() {
		return "Paid ₹" + finalAmount + " via UPI : " + upiId;
	}

}
