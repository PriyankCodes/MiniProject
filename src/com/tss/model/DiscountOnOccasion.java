package com.tss.model;

public class DiscountOnOccasion implements IDiscount {

	double percent;

	public DiscountOnOccasion(double percent) {
		this.percent = percent;
	}

	@Override
	public double applyDiscount(double total) {
		// TODO Auto-generated method stub
		return 0;
	}

}
