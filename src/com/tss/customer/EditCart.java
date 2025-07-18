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
		for (int i = 0; i < itemList.size(); i++) {
			FoodItem item = itemList.get(i);
			System.out.println((i + 1) + ". " + item.getName() + " - Current Qty: " + cart.get(item));
		}

		System.out.print("Select item to update : ");
		int index = scanner.nextInt();
		scanner.nextLine();

		if (index < 1 || index > itemList.size()) {
			System.out.println("Invalid selection.");
			return;
		}

		FoodItem selected = itemList.get(index - 1);
		System.out.print("Enter new quantity (0 to remove): ");
		int newQty = scanner.nextInt();

		if (newQty == 0) {
			cart.remove(selected);
			System.out.println("Item removed from cart.");
		} else if (newQty > 0) {
			cart.put(selected, newQty);
			System.out.println("Quantity updated.");
		} else {
			System.out.println("Invalid quantity.");
		}

		order.calculateTotals(); 
	}
}
