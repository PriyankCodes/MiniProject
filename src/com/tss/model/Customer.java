// File: com.tss.model.Customer.java
package com.tss.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tss.util.ObjectLoad;

public class Customer {

	private static int counter = 101;

	private int customerId;
	private String name, email, phone, address;
	private List<Order> orders;
	private List<IMenu> menus;
	private static final String MENU_FILE = "menus.ser";
	private static final String DELIVERY_FILE = "delivery.ser";

	Scanner scanner = new Scanner(System.in);

	public Customer(String name, String email, String phone, String address) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		menus = ObjectLoad.load(MENU_FILE);
		orders = new ArrayList<>();
		customerId = counter++;
	}

	public void showCustomerMenu() {
		int choice;
		do {
			System.out.println("\n--- Customer Menu ---");
			System.out.println("1. View Menu");
			System.out.println("2. Place Order");
			System.out.println("3. Exit");
			System.out.print("Enter Your Choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1 -> showMenu();
			case 2 -> placeOrder();
			case 3 -> System.out.println("Thank you for visiting!");
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 3);
	}

	private void showMenu() {
		if (menus == null || menus.isEmpty()) {
			System.out.println("No menus available.");
			return;
		}

		for (int i = 0; i < menus.size(); i++) {
			IMenu menu = menus.get(i);
			System.out.println((i + 1) + ". " + menu.getClass().getSimpleName());
			for (FoodItem item : menu.getMenuItems()) {
				System.out.println("   - " + item);
			}
		}
	}

	private void placeOrder() {
		if (menus == null || menus.isEmpty()) {
			System.out.println("No menus to order from.");
			return;
		}

		Order order = new Order();
		order.setCustomerId(this.customerId);

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
			order.addItem(selectedItem);
		}

		payment(order);

		List<IDeliveryAgents> partners = ObjectLoad.load(DELIVERY_FILE);
		IDeliveryAgents partner = partners.get(new Random().nextInt(partners.size()));
		order.setDeliveryPartner(partner);

		orders.add(order);

		InvoicePrinter printer = new InvoicePrinter();
		printer.printInvoice(order);
	}

	private void payment(Order order) {
		System.out.println("\nSelect Payment Method:");
		System.out.println("1. UPI");
		System.out.println("2. Card");
		System.out.println("3. Cash");
		System.out.print("Choice: ");
		int payChoice = scanner.nextInt();

		IPayment payment = null;
		switch (payChoice) {
		case 1 -> {
			System.out.print("Enter UPI ID: ");
			String upiId = scanner.next();

			System.out.print("Enter pin: ");
			String pin = scanner.next();
			payment = new UPIPayment(upiId, pin, order.getFinalAmount());
		}
		case 2 -> {
			System.out.print("Enter Card Number: ");
			String cardNumber = scanner.next();

			System.out.print("Enter pin: ");
			String pin = scanner.next();
			payment = new CardPayment(cardNumber, pin, order.getFinalAmount());
		}
		case 3 -> {
			payment = new CashPayment(order.getFinalAmount());
		}
		default -> {
			System.out.println("Invalid payment method. Aborting order.");
			return;
		}
		}
		order.setPayment(payment);
	}
}
