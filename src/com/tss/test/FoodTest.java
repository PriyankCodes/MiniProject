package com.tss.test;

import java.util.Scanner;

import com.tss.model.Admin;
import com.tss.model.Customer;

public class FoodTest {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			System.out.print("Are you:\n1. Admin\n2. Customer\n3. Exit => ");
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

		System.out.print("Enter Your Name : ");
		String name = scanner.next();

		System.out.print("Enter Your Email : ");
		String email = scanner.next();

		System.out.print("Enter Your phone : ");
		String phone = scanner.next();

		System.out.print("Enter Your Address : ");
		String address = scanner.next();

		Customer customer = new Customer(name, email, phone, address);
		customer.showCustomerMenu();
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
