package com.tss.model;

public class UPIPayment implements IPayment {

	private String upiId;
	private String pin;
	private double finalAmount;

	public UPIPayment(String upiId, String pin, double finalAmount) {
		this.upiId = upiId;
		this.pin = pin;
		this.finalAmount = finalAmount;
	}

	public void pay() {
		System.out.println("Paid ₹" + finalAmount + " via UPI : " + upiId);
	}

}
