package com.tss.model;

public class Discount implements IDiscount {

	private static final long serialVersionUID = 1L;
	private double flatPercentage;

	public Discount(double flatPercentage) {
		this.flatPercentage = flatPercentage;
	}

	@Override
	public double applyDiscount(double totalAmount) {
		if (totalAmount > 500) {
			return totalAmount * (flatPercentage / 100);
		}
		return 0;
	}
}
