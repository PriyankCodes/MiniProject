package com.tss.discount;

import java.io.Serializable;

public interface IDiscount extends Serializable {
	double applyDiscount(double amount);
}
