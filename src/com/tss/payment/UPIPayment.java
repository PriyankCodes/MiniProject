package com.tss.payment;

import com.tss.model.IPayment;

public class UPIPayment implements IPayment {

	private String upiId;
	private String pin;
	private double finalAmount;

	public UPIPayment(String upiId, String pin, double finalAmount) {
		this.upiId = upiId;
		this.pin = pin;
		this.finalAmount = finalAmount;
	}

	public String pay() {
		return "Paid ₹" + finalAmount + " via UPI : " + upiId;
	}

}
