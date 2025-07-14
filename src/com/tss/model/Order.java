// File: com.tss.model.Order.java
package com.tss.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private static int counter = 1;
	private int orderId;
	private int customerId;
	private List<FoodItem> orderedItems;
	private double totalAmount;
	private double discountApplied;
	private IDeliveryAgents deliveryPartner;
	private IPayment payment;

	public Order() {
		this.orderId = counter++;
		this.orderedItems = new ArrayList<>();
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setDiscountApplied(double discount) {
		this.discountApplied = discount;
	}

	public void addItem(FoodItem item) {
		orderedItems.add(item);
		totalAmount += item.getPrice();
	}

	public void setDeliveryPartner(IDeliveryAgents partner) {
		this.deliveryPartner = partner;
	}

	public void setPayment(IPayment payment) {
		this.payment = payment;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public List<FoodItem> getOrderedItems() {
		return orderedItems;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public double getDiscountApplied() {
		return discountApplied;
	}

	public double getFinalAmount() {
		return totalAmount - discountApplied;
	}

	public IDeliveryAgents getDeliveryPartner() {
		return deliveryPartner;
	}

	public IPayment getPayment() {
		return payment;
	}

}
