package com.tss.model;

import java.io.Serializable;

public interface IDiscount extends Serializable {
	double applyDiscount(double amount);
}
