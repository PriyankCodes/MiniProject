package com.tss.admin;

import java.util.List;
import java.util.Scanner;

import com.tss.menus.ChineseMenu;
import com.tss.menus.IMenu;
import com.tss.menus.IndianMenu;
import com.tss.menus.ItalianMenu;
import com.tss.util.ObjectStore;

public class AdminMenu {
	private final List<IMenu> menus;
	private final String MENU_FILE = "menus.ser";
	private final Scanner scanner = new Scanner(System.in);

	public AdminMenu(List<IMenu> menus) {
		this.menus = menus;
	}

	public void menuManagement() {
		int choice;
		do {
			System.out.println("\n--- Menu Management ---");
			System.out.println("1. Add Menu");
			System.out.println("2. View Menus");
			System.out.println("3. Remove Menu");
			System.out.println("4. Back");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1 -> addMenu();
			case 2 -> viewMenus();
			case 3 -> removeMenu();
			case 4 -> System.out.println("Returning to main menu.");
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 4);
	}

	private void addMenu() {
		System.out.println("Select Menu to Add:");
		System.out.println("1. Indian Menu");
		System.out.println("2. Chinese Menu");
		System.out.println("3. Italian Menu");
		System.out.print("Enter choice: ");
		int choice = scanner.nextInt();

		IMenu newMenu = null;

		switch (choice) {
		case 1 -> newMenu = new IndianMenu();
		case 2 -> newMenu = new ChineseMenu();
		case 3 -> newMenu = new ItalianMenu();
		default -> {
			System.out.println("Invalid choice.");
			return;
		}
		}

		for (IMenu menu : menus) {
			if (menu.getClass().equals(newMenu.getClass())) {
				System.out.println(newMenu.getClass().getSimpleName() + " already exists. Duplicate not allowed.");
				return;
			}
		}

		if (newMenu != null) {

			menus.add(newMenu);
			ObjectStore.save(MENU_FILE, menus);
			System.out.println(newMenu.getClass().getSimpleName() + " added successfully.");
		}
	}

	private void removeMenu() {
		viewMenus();
		System.out.print("Enter menu number to remove: ");
		int index = scanner.nextInt();
		if (index < 1 || index > menus.size()) {
			System.out.println("Invalid menu selection.");
			return;
		}
		menus.remove(index - 1);
		ObjectStore.save(MENU_FILE, menus);
		System.out.println("Menu removed successfully.");
	}

	private void viewMenus() {
		if (menus.isEmpty()) {
			System.out.println("No menus available.");
			return;
		}
		System.out.println();
		System.out.printf("%-5s %-20s%n", "No.", "Menu Name");
		System.out.println("------------------------");
		for (int i = 0; i < menus.size(); i++) {
			System.out.printf("%-5d %-20s%n", (i + 1), menus.get(i).getClass().getSimpleName());
		}
	}

}
