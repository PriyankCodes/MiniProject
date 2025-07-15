package com.tss.validate;

import java.util.Scanner;

public class ValidatePayment {
	Scanner scanner = new Scanner(System.in);

	public String getValidUPI() {
		String upi;
		do {
			System.out.print("Enter UPI ID (e.g., name@bank): ");
			upi = scanner.nextLine().trim();
		} while (!upi.matches("^[\\w.-]+@[\\w.-]+$"));
		return upi;
	}

	public String getValidCardNumber() {
		String card;
		do {
			System.out.print("Enter 16-digit Card Number: ");
			card = scanner.nextLine().trim();
		} while (!card.matches("\\d{16}"));
		return card;
	}

	public String getValidPin() {
		String pin;
		do {
			System.out.print("Enter 4-digit PIN: ");
			pin = scanner.nextLine().trim();
		} while (!pin.matches("\\d{4}"));
		return pin;
	}

}
