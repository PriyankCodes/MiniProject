package com.tss.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.IMenu;
import com.tss.orders.Order;
import com.tss.util.ObjectLoad;

public class Customer {

	private static int counter = 101;

	private int customerId;
	private String name, email, phone, address;
	private List<Order> orders;

	private static final String MENU_FILE = "menus.ser";
	private List<IMenu> menus = ObjectLoad.load(MENU_FILE);;

	private Order currentOrder = new Order();
	Scanner scanner = new Scanner(System.in);

	public Customer(String name, String email, String phone, String address) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		orders = new ArrayList<>();
		customerId = counter++;
	}

	public void showCustomerMenu() {
		int choice;
		do {
			System.out.println("\n--- Customer Menu ---");
			System.out.println("1. View Menu");
			System.out.println("2. Add to Cart");
			System.out.println("3. Checkout");
			System.out.println("4. Exit");
			System.out.print("Enter Your Choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1 -> new ViewMenu(menus).showMenu();
			case 2 -> new CustomerCart(menus, currentOrder).addToCart();
			case 3 -> {
				new CustomerPayment(currentOrder, orders, this).checkout();
				return;
			}
			case 4 -> System.out.println("Thank you for visiting!");
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 4);
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

}
