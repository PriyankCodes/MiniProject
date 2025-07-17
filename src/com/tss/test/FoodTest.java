package com.tss.test;

import java.util.Scanner;

import com.tss.admin.Admin;
import com.tss.customer.Customer;
import com.tss.customer.CustomerLogin;

public class FoodTest {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			System.out.print("\nAre you:\n1. Admin\n2. Customer\n3. Exit => ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				admin();
				break;
			case 2:
				customer();
				break;
			case 3:
				System.out.println("Thanks for using the app!");
				return;

			default:
				System.out.println("Invalid input.");
			}
		}

	}

	private static void customer() {

		CustomerLogin login = new CustomerLogin();
		Customer customer = login.authenticate();

		if (customer != null) {
			customer.showCustomerMenu();
		} else {
			System.out.println("Login failed.");
		}
	}

	private static void admin() {
		Admin admin = new Admin("1", "123456");

		System.out.print("\nEnter ID : ");
		String id = scanner.next();

		System.out.print("Enter Password : ");
		String password = scanner.next();

		if (admin.authenticate(id, password)) {

			admin.showAdminMenu();
		}
	}

}
