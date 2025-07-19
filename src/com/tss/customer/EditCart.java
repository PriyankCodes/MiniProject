package com.tss.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.tss.menus.FoodItem;
import com.tss.orders.Order;

public class EditCart {
	private final Order order;
	private final Scanner scanner = new Scanner(System.in);

	public EditCart(Order order) {
		this.order = order;
	}

	public void editCartQuantity() {
		Map<FoodItem, Integer> cart = order.getItemQuantityMap();
		if (cart.isEmpty()) {
			System.out.println("Cart is empty.");
			return;
		}

		List<FoodItem> itemList = new ArrayList<>(cart.keySet());

		System.out.println("\n--- Edit Cart Quantities ---");
		System.out.printf("%-5s %-20s %-15s%n", "No.", "Item Name", "Current Quantity");
		System.out.println("-----------------------------------------");
		for (int i = 0; i < itemList.size(); i++) {
			FoodItem item = itemList.get(i);
			System.out.printf("%-5d %-20s %-15d%n", (i + 1), item.getName(), cart.get(item));
		}

		System.out.print("Select item to update (number): ");
		int index = scanner.nextInt();

		if (index < 1 || index > itemList.size()) {
			System.out.println("Invalid selection.");
			return;
		}

		FoodItem selected = itemList.get(index - 1);
		System.out.print("Enter new quantity (0 to remove): ");
		int newQty = scanner.nextInt();

		if (newQty < 0) {
			System.out.println("Quantity cannot be negative.");
			return;
		}

		if (newQty == 0) {
			cart.remove(selected);
			System.out.println("Item removed from cart.");
		} else {
			cart.put(selected, newQty);
			System.out.println("Quantity updated.");
		}

		order.calculateTotals(); // Update total after edit
	}
}
