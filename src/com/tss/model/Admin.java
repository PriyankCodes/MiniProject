package com.tss.model;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import com.tss.util.ObjectLoad;
import com.tss.util.ObjectStore;

public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	private String adminId;
	private String password;

	private static final String MENU_FILE = "menus.ser";
	private static final String DISCOUNT_FILE = "discounts.ser";
	private static final String DELIVERY_FILE = "delivery.ser";

	private List<IMenu> menus = ObjectLoad.load(MENU_FILE);
	private List<IDiscount> discounts = ObjectLoad.load(DISCOUNT_FILE);
	private List<IDeliveryAgents> deliveryAgents = ObjectLoad.load(DELIVERY_FILE);

	Scanner scanner = new Scanner(System.in);

	public Admin(String adminId, String password) {
		this.adminId = adminId;
		this.password = password;

	}

	public boolean authenticate(String inputId, String inputPass) {
		return this.adminId.equals(inputId) && this.password.equals(inputPass);
	}

	public void showAdminMenu() {
		int choice;
		do {
			System.out.println("\n--- Admin Menu ---");
			System.out.println("1. Add Menu");
			System.out.println("2. View Menus");
			System.out.println("3. Add Food Item to Menu");
			System.out.println("4. Add Discount");
			System.out.println("5. Add Delivery Agent ");
			System.out.println("6. View Delivery Agents");
			System.out.println("7. Logout");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1 -> addMenu();
			case 2 -> viewMenus();
			case 3 -> addFoodItemToMenu();
			case 4 -> addDiscount();
			case 5 -> addDeliveryAgent();
			case 6 -> viewDeliveryAgents();
			case 7 -> {

				ObjectStore.save(MENU_FILE, menus);
				ObjectStore.save(DISCOUNT_FILE, discounts);
				ObjectStore.save(DELIVERY_FILE, deliveryAgents);
				System.out.println("Logged out.");
			}
			default -> System.out.println("Invalid option.");
			}
		} while (choice != 7);
	}

	private void addMenu() {
		System.out.println("Select Menu to Add:");
		System.out.println("1. Indian Menu");
		System.out.println("2. Chinese Menu");
		System.out.println("3. Italian Menu");
		System.out.print("Enter choice: ");
		int choice = scanner.nextInt();

		IMenu menu = null;
		switch (choice) {
		case 1 -> menu = new IndianMenu();
		case 2 -> menu = new ChineseMenu();
		case 3 -> menu = new ItalianMenu();
		default -> {
			System.out.println("Invalid choice.");
			return;
		}
		}

		menus.add(menu);
		ObjectStore.save(MENU_FILE, menus);

		System.out.println(menu.getClass().getSimpleName() + " added successfully.");
	}

	private void viewMenus() {

		if (menus.isEmpty()) {
			System.out.println("No menus available.");
			return;
		}

		for (int i = 0; i < menus.size(); i++) {
			IMenu menu = menus.get(i);
			System.out.println((i + 1) + ". " + menu);
			for (FoodItem item : menu.getMenuItems()) {
				System.out.println("   " + item);
			}
		}
	}

	private void addFoodItemToMenu() {
		if (menus.isEmpty()) {
			System.out.println("No menus found. Add one first.");
			return;
		}

		for (int i = 0; i < menus.size(); i++) {
			System.out.println((i + 1) + ". " + menus.get(i));
		}

		System.out.print("Select a menu: ");
		int menuIndex = scanner.nextInt();
		scanner.nextLine();

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

		FoodItem item = new FoodItem(id, name, price, desc);
		menus.get(menuIndex - 1).getMenuItems().add(item);
		ObjectStore.save(MENU_FILE, menus);

		System.out.println("Food item added to menu.");
	}

	private void addDiscount() {
		System.out.println("1. Occasion Discount");
		System.out.println("2. Monthly Discount");
		int type = scanner.nextInt();

		if (type == 1) {
			System.out.print("Enter percentage: ");
			double percent = scanner.nextDouble();
			discounts.add(new DiscountOnOccasion(percent));
		} else if (type == 2) {
			System.out.print("Enter flat percentage : ");
			double flat = scanner.nextDouble();
			discounts.add(new DiscountMonthly(flat));
		}
		ObjectStore.save(DISCOUNT_FILE, discounts);
		System.out.println("Discount added.");
	}

	private void addDeliveryAgent() {
		System.out.println("Select Agent to Add:");
		System.out.println("1. Swiggy");
		System.out.println("2. Zomato");
		System.out.print("Enter choice: ");
		int choice = scanner.nextInt();

		IDeliveryAgents agent = null;

		switch (choice) {
		case 1:
			agent = new Swiggy();
			break;
		case 2:
			agent = new Zomato();
			break;
		default:
			System.out.println("Invalid Agent");
			return;
		}

		deliveryAgents.add(agent);

		ObjectStore.save(DELIVERY_FILE, deliveryAgents);
		System.out.println(agent.getClass().getSimpleName() + " Added Successfully.");
	}

	private void viewDeliveryAgents() {

		if (deliveryAgents.isEmpty()) {
			System.out.println("No delivery agents added.");
			return;
		}

		for (IDeliveryAgents agent : deliveryAgents) {
			System.out.println(agent.getClass().getSimpleName());
		}
	}

	public List<IMenu> getMenus() {
		return menus;
	}

	public List<IDiscount> getDiscounts() {
		return discounts;
	}

	public List<IDeliveryAgents> getDeliveryAgents() {
		return deliveryAgents;
	}
}
