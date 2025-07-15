package com.tss.orders;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tss.model.FoodItem;
import com.tss.model.IDeliveryAgents;
import com.tss.model.IDiscount;
import com.tss.model.IPayment;
import com.tss.util.ObjectLoad;

public class Order {

	private static int counter = 1001;
	private static final String DISCOUNT_FILE = "discounts.ser";

	private int orderId;
	private Map<FoodItem, Integer> itemQuantityMap = new LinkedHashMap<>();
	private IPayment payment;
	private IDeliveryAgents deliveryPartner;

	private double totalAmount;
	private double discountApplied;
	private double finalAmount;

	public Order() {
		orderId = counter++;
	}

	public void addItemWithQuantity(FoodItem item, int quantity) {
		if (itemQuantityMap.containsKey(item)) {
			int currentQty = itemQuantityMap.get(item);
			itemQuantityMap.put(item, currentQty + quantity);
		} else {
			itemQuantityMap.put(item, quantity);
		}

		calculateTotals();
	}

	public void calculateTotals() {
		totalAmount = 0.0;

		for (Map.Entry<FoodItem, Integer> entry : itemQuantityMap.entrySet()) {
			FoodItem item = entry.getKey();
			int qty = entry.getValue();
			totalAmount += item.getPrice() * qty;
		}
		discountApplied = 0.0;

		List<IDiscount> discounts = ObjectLoad.load(DISCOUNT_FILE);
		if (discounts != null && !discounts.isEmpty()) {
			IDiscount discount = discounts.get(0);
			discountApplied = discount.applyDiscount(totalAmount);
		}

		finalAmount = totalAmount - discountApplied;
	}

	public int getOrderId() {
		return orderId;
	}



	public Map<FoodItem, Integer> getItemQuantityMap() {
		return itemQuantityMap;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public double getDiscountApplied() {
		return discountApplied;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setPayment(IPayment payment) {
		this.payment = payment;
	}

	public IPayment getPayment() {
		return payment;
	}

	public IDeliveryAgents getDeliveryPartner() {
		return deliveryPartner;
	}

	public void setDeliveryPartner(IDeliveryAgents deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}
}
