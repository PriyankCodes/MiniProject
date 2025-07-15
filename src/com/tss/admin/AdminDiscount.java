package com.tss.admin;

import java.util.List;
import java.util.Scanner;

import com.tss.model.Discount;
import com.tss.model.IDiscount;
import com.tss.util.ObjectStore;

public class AdminDiscount {

	private final List<IDiscount> discounts;
	private final String DISCOUNT_FILE = "discounts.ser";
	private final Scanner scanner = new Scanner(System.in);

	public AdminDiscount(List<IDiscount> discounts) {
		this.discounts = discounts;
	}

	public void discountManagement() {
		int choice;
		do {
			System.out.println("\n--- Discount Management ---");
			System.out.println("1. Add Discount");
			System.out.println("2. Remove Discount");
			System.out.println("3. Back");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1 -> addDiscount();
			case 2 -> removeDiscount();
			case 3 -> System.out.println("Returning to main menu.");
			default -> System.out.println("Invalid option.");
			}
		} while (choice != 3);
	}

	private void addDiscount() {
		if (!discounts.isEmpty()) {
			System.out.println("A discount is already added. Remove it first.");
			return;
		}
		System.out.print("Enter discount %: ");
		double percent = scanner.nextDouble();
		discounts.add(new Discount(percent));
		ObjectStore.save(DISCOUNT_FILE, discounts);
		System.out.println("Discount added.");
	}

	private void removeDiscount() {
		if (discounts.isEmpty()) {
			System.out.println("No discounts to remove.");
			return;
		}
		discounts.clear();
		ObjectStore.save(DISCOUNT_FILE, discounts);
		System.out.println("Discount removed.");
	}
}
