package com.tss.test;

import java.util.Scanner;

public class InputValidator {
	private static Scanner scanner = new Scanner(System.in);

	public static String getValidName() {
		String name;
		do {
			System.out.print("Enter Your Name: ");
			name = scanner.nextLine().trim();
		} while (name.isEmpty());
		return name;
	}

	public static String getValidEmail() {
		String email;
		do {
			System.out.print("Enter Your Email: ");
			email = scanner.nextLine().trim();
		} while (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$"));
		return email;
	}

	public static String getValidPhone() {
		String phone;
		do {
			System.out.print("Enter Your Phone: ");
			phone = scanner.nextLine().trim();
		} while (!phone.matches("\\d{10}"));
		return phone;
	}

	public static String getValidAddress() {
		String address;
		do {
			System.out.print("Enter Your Address: ");
			address = scanner.nextLine().trim();
		} while (address.isEmpty());
		return address;
	}
}
