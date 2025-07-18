package com.tss.customer;

import java.util.Map;

import com.tss.exception.EmptyCartException;
import com.tss.menus.FoodItem;
import com.tss.orders.Order;

public class ViewCart {

	Order order;

	public ViewCart(Order order) {
		super();
		this.order = order;
	}

	public void viewCart() {

		if (order.getItemQuantityMap().isEmpty()) {
			throw new EmptyCartException();
		}
		System.out.println("\nItems In Cart :");

		for (Map.Entry<FoodItem, Integer> entry : order.getItemQuantityMap().entrySet()) {
			System.out.println("  - " + entry.getKey() + " (x" + entry.getValue() + ")");
		}
		System.out.println("\n\tTotal Amount  : ₹" + order.getTotalAmount());

	}

}
