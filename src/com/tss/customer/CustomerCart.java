package com.tss.customer;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import com.tss.model.FoodItem;
import com.tss.model.IMenu;
import com.tss.orders.Order;

public class CustomerCart implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Scanner scanner = new Scanner(System.in);
	private final List<IMenu> menus;
	private final Order currentOrder;

	public CustomerCart(List<IMenu> menus, Order currentOrder) {
		this.menus = menus;
		this.currentOrder = currentOrder;
	}

	public void addToCart() {
		if (menus == null || menus.isEmpty()) {
			System.out.println("No menus to order from.");
			return;
		}

		System.out.println("\nSelect a Menu:");
		for (int i = 0; i < menus.size(); i++) {
			System.out.println((i + 1) + ". " + menus.get(i).getClass().getSimpleName());
		}

		System.out.print("Enter choice: ");
		int menuIndex = scanner.nextInt();

		if (menuIndex < 1 || menuIndex > menus.size()) {
			System.out.println("Invalid menu selection.");
			return;
		}

		IMenu selectedMenu = menus.get(menuIndex - 1);
		List<FoodItem> items = selectedMenu.getMenuItems();

		while (true) {
			System.out.println("\nAvailable Items:");
			for (int i = 0; i < items.size(); i++) {
				System.out.println((i + 1) + ". " + items.get(i));
			}

			System.out.print("Select item number (0 to finish): ");
			int itemChoice = scanner.nextInt();
			if (itemChoice == 0)
				break;

			if (itemChoice < 1 || itemChoice > items.size()) {
				System.out.println("Invalid item. Try again.");
				continue;
			}

			FoodItem selectedItem = items.get(itemChoice - 1);
			System.out.print("Enter quantity: ");
			int quantity = scanner.nextInt();

			currentOrder.addItemWithQuantity(selectedItem, quantity);
			System.out.println("Added to cart.");
		}
	}
}
