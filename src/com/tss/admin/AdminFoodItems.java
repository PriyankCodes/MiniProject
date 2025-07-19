package com.tss.admin;

import java.util.List;
import java.util.Scanner;

import com.tss.exception.NoMenuAvailableException;
import com.tss.menus.FoodItem;
import com.tss.menus.IMenu;
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
			case 1 -> {
				try {
					addFoodItem();
				} catch (NoMenuAvailableException exception) {
					System.out.println(exception.getMessage());
				}
			}
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

		IMenu menu = menus.get(menuIndex - 1);

		int id = getNextFoodId(menu);

		System.out.println("Auto-generated Food ID: " + id);

		System.out.print("Enter Name: ");
		String name = scanner.nextLine();

		for (FoodItem foodItem : menu.getMenuItems()) {
			if (foodItem.getName().equalsIgnoreCase(name)) {
				System.out.println("Food item already exists.");
				return;
			}
		}

		System.out.print("Enter Price: ");
		double price = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter Description: ");
		String description = scanner.nextLine();

		menu.getMenuItems().add(new FoodItem(id, name, price, description));
		ObjectStore.save(MENU_FILE, menus);
		System.out.println("Food item added.");
	}

	private int getNextFoodId(IMenu menu) {
		int maxId = 0;
		for (FoodItem item : menu.getMenuItems()) {
			if (item.getId() > maxId) {
				maxId = item.getId();
			}
		}
		return maxId + 1;
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
		System.out.println();
		System.out.printf("%-5s %-25s %-10s %-40s%n", "No", "Name", "Price", "Description");
		System.out.println("-------------------------------------------------------------------------------");
		for (int i = 0; i < items.size(); i++) {
			FoodItem item = items.get(i);
			System.out.printf("%-5d %-25s %-10.2f %-40s%n", i + 1, item.getName(), item.getPrice(),
					item.getDescription());
		}

		System.out.print("\nSelect item to edit: ");
		int index = scanner.nextInt();
		scanner.nextLine();
		if (index < 1 || index > items.size()) {
			System.out.println("Invalid item.");
			return;
		}

		FoodItem item = items.get(index - 1);
		System.out.print("Enter new name (press Enter to keep unchanged): ");
		String name = scanner.nextLine();
		if (!name.isBlank()) {
			item.setName(name);
		}
		System.out.print("Enter new price (press Enter to keep unchanged): ");
		String priceInput = scanner.nextLine();
		if (!priceInput.isBlank()) {
			try {
				double price = Double.parseDouble(priceInput);
				if (price > 0) {
					item.setPrice(price);
				} else {
					System.out.println("Invalid price input. Keeping old price.");

				}
			} catch (Exception e) {
				System.out.println("Invalid price input. Keeping old price.");
			}
		}

		System.out.print("Enter new description (press Enter to keep unchanged): ");
		String desc = scanner.nextLine();
		if (!desc.isBlank()) {
			item.setDescription(desc);
		}
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
		System.out.println();
		List<FoodItem> items = menus.get(menuIndex - 1).getMenuItems();
		System.out.printf("%-5s %-25s %-10s %-40s%n", "No", "Name", "Price", "Description");
		System.out.println("-------------------------------------------------------------------------------");
		for (int i = 0; i < items.size(); i++) {
			FoodItem item = items.get(i);
			System.out.printf("%-5d %-25s %-10.2f %-40s%n", i + 1, item.getName(), item.getPrice(),
					item.getDescription());
		}

		System.out.print("\nSelect item to remove: ");
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
			System.out.println("\n=> " + menu.getClass().getSimpleName() );
			System.out.printf("%-5s %-25s %-10s %-40s%n", "ID", "Name", "Price", "Description");
			System.out.println("-------------------------------------------------------------------------------");
			for (FoodItem item : menu.getMenuItems()) {
				System.out.printf("%-5d %-25s %-10.2f %-40s%n", item.getId(), item.getName(), item.getPrice(),
						item.getDescription());
			}
		}
	}

	private void viewMenus() {
		if (menus.isEmpty()) {
			throw new NoMenuAvailableException();
		}
		for (int i = 0; i < menus.size(); i++) {
			System.out.println((i + 1) + ". " + menus.get(i).getClass().getSimpleName());
		}
	}
}
