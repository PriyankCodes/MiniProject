package com.tss.admin;

import java.util.List;
import java.util.Scanner;

import com.tss.model.FoodItem;
import com.tss.model.IMenu;
import com.tss.util.ObjectStore;

public class AdminFoodItems {
	private final List<IMenu> menus;
	private final String MENU_FILE = "menus.ser";
	private final Scanner scanner = new Scanner(System.in);

	public AdminFoodItems(List<IMenu> menus) {
		this.menus = menus;
	}

	public void foodItemManagement() {
		int choice;
		do {
			System.out.println("\n--- Food Item Management ---");
			System.out.println("1. Add Food Item");
			System.out.println("2. Edit Food Item");
			System.out.println("3. Remove Food Item");
			System.out.println("4. View All Items");
			System.out.println("5. Back");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1 -> addFoodItem();
			case 2 -> editFoodItem();
			case 3 -> removeFoodItem();
			case 4 -> viewAllItems();
			case 5 -> System.out.println("Returning to Admin menu.");
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 5);
	}

	private void addFoodItem() {
		viewMenus();
		System.out.print("Select menu: ");
		int menuIndex = scanner.nextInt();
		scanner.nextLine();
		if (menuIndex < 1 || menuIndex > menus.size()) {
			System.out.println("Invalid menu.");
			return;
		}

		System.out.print("Enter Food ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Price: ");
		double price = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter Description: ");
		String desc = scanner.nextLine();

		IMenu menu = menus.get(menuIndex - 1);
		for (FoodItem foodItem : menu.getMenuItems()) {
			if (foodItem.getId() == id || foodItem.getName().equalsIgnoreCase(name)) {
				System.out.println("Food item already exists.");
				return;
			}
		}

		menu.getMenuItems().add(new FoodItem(id, name, price, desc));
		ObjectStore.save(MENU_FILE, menus);
		System.out.println("Food item added.");
	}

	private void editFoodItem() {
		viewMenus();
		System.out.print("Select menu: ");
		int menuIndex = scanner.nextInt();
		if (menuIndex < 1 || menuIndex > menus.size()) {
			System.out.println("Invalid menu.");
			return;
		}
		List<FoodItem> items = menus.get(menuIndex - 1).getMenuItems();
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i + 1) + ". " + items.get(i));
		}
		System.out.print("Select item to edit: ");
		int index = scanner.nextInt();
		scanner.nextLine();
		if (index < 1 || index > items.size()) {
			System.out.println("Invalid item.");
			return;
		}

		FoodItem item = items.get(index - 1);
		System.out.print("Enter new name: ");
		String name = scanner.nextLine();
		System.out.print("Enter new price: ");
		double price = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter new description: ");
		String desc = scanner.nextLine();

		item.setName(name);
		item.setPrice(price);
		item.setDescription(desc);
		ObjectStore.save(MENU_FILE, menus);
		System.out.println("Item updated.");
	}

	private void removeFoodItem() {
		viewMenus();
		System.out.print("Select menu: ");
		int menuIndex = scanner.nextInt();
		if (menuIndex < 1 || menuIndex > menus.size()) {
			System.out.println("Invalid menu.");
			return;
		}
		List<FoodItem> items = menus.get(menuIndex - 1).getMenuItems();
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i + 1) + ". " + items.get(i));
		}
		System.out.print("Select item to remove: ");
		int index = scanner.nextInt();
		if (index < 1 || index > items.size()) {
			System.out.println("Invalid item.");
			return;
		}
		items.remove(index - 1);
		ObjectStore.save(MENU_FILE, menus);
		System.out.println("Item removed.");
	}

	private void viewAllItems() {
		viewMenus();
		for (IMenu menu : menus) {
			System.out.println(menu.getClass().getSimpleName() + ":");
			for (FoodItem item : menu.getMenuItems()) {
				System.out.println("   - " + item);
			}
		}
	}

	private void viewMenus() {
		if (menus.isEmpty()) {
			System.out.println("No menus found.");
			return;
		}
		for (int i = 0; i < menus.size(); i++) {
			System.out.println((i + 1) + ". " + menus.get(i).getClass().getSimpleName());
		}
	}
}
