package com.tss.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.exception.EmptyCartException;
import com.tss.exception.NoMenuAvailableException;
import com.tss.orders.Order;
import com.tss.util.ObjectLoad;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	static Scanner scanner = new Scanner(System.in);

	private static int counter = 0;

	private int customerId;
	private String name, email, phone, address, password;
	private List<Order> orders;

	private static final String MENU_FILE = "menus.ser";

	private Order currentOrder = new Order();

	public Customer(String name, String email, String phone, String address, String password) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		orders = new ArrayList<>();

		if (counter == 0) {
			List<Customer> allCustomers = ObjectLoad.load("customers.ser");
			counter = allCustomers != null ? allCustomers.size() + 101 : 101;
		}
		this.customerId = counter++;
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
			case 1 -> {
				try {
					new ViewMenu(ObjectLoad.load(MENU_FILE)).showMenu();
				} catch (NoMenuAvailableException exception) {
					System.out.println(exception.getMessage());

				}
			}
			case 2 -> new CustomerCart(ObjectLoad.load(MENU_FILE), currentOrder).addToCart();
			case 3 -> {
				try {
					new CustomerPayment(currentOrder, orders, this).checkout();
					return;

				} catch (EmptyCartException exception) {
					System.out.println(exception.getMessage());
				}
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

	public String getPassword() {
		return password;
	}

}
