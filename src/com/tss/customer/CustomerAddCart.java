package com.tss.customer;

import java.util.List;
import java.util.Scanner;

import com.tss.menus.FoodItem;
import com.tss.menus.IMenu;
import com.tss.orders.Order;

public class CustomerAddCart {

	private final Scanner scanner = new Scanner(System.in);
	private final List<IMenu> menus;
	private final Order currentOrder;

	public CustomerAddCart(List<IMenu> menus, Order currentOrder) {
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

			System.out.print("\nSelect item number (0 to finish): ");
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

			currentOrder.addItem(selectedItem, quantity);
			System.out.println("Added to cart.");
		}
	}
}
