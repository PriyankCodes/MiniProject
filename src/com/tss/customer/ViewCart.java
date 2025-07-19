package com.tss.customer;

import java.util.Map;

import com.tss.exception.EmptyCartException;
import com.tss.menus.FoodItem;
import com.tss.orders.Order;

public class ViewCart {

	private final Order order;

	public ViewCart(Order order) {
		this.order = order;
	}

	public void viewCart() {
		if (order.getItemQuantityMap().isEmpty()) {
			throw new EmptyCartException();
		}

		System.out.println("\nItems In Cart:");
		System.out.printf("%-20s %-10s %-10s %-30s%n", "Item Name", "Price", "Quantity", "Description");
		System.out.println("--------------------------------------------------------------------------");

		for (Map.Entry<FoodItem, Integer> entry : order.getItemQuantityMap().entrySet()) {
			FoodItem item = entry.getKey();
			int quantity = entry.getValue();
			System.out.printf("%-20s %-10.2f %-10d %-30s%n", item.getName(), item.getPrice(), quantity,
					item.getDescription());
		}

		System.out.println("--------------------------------------------------------------------------");
		System.out.printf("\n%-30s ₹%.2f%n", "Total Amount:", order.getTotalAmount());
	}
}
