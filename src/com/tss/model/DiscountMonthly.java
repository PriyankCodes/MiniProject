package com.tss.model;

public class DiscountMonthly implements IDiscount {

	double flatPercent;

	public DiscountMonthly(double flatPercent) {
		this.flatPercent = flatPercent;
	}

	@Override
	public double applyDiscount(double total) {
		// TODO Auto-generated method stub
		return 0;
	}

}
