package com.tss.payment;

import com.tss.model.IPayment;

public class CashPayment implements IPayment {

	private double finalAmount;

	public CashPayment(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String pay() {
		return "Paid ₹" + finalAmount + " via Cash";
	}

}
