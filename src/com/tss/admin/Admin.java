package com.tss.admin;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import com.tss.exception.NoMenuAvailableException;
import com.tss.model.IDeliveryAgents;
import com.tss.model.IDiscount;
import com.tss.model.IMenu;
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
			System.out.println("1. Manage Menus");
			System.out.println("2. Manage Food Items");
			System.out.println("3. Manage Discounts");
			System.out.println("4. Manage Delivery Agents");
			System.out.println("5. Logout");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1 -> new AdminMenu(menus).menuManagement();
			case 2 -> {
				try {
					new AdminFoodItems(menus).foodItemManagement();
				} catch (NoMenuAvailableException exception) {
					System.out.println(exception.getMessage());
				}
			}
			case 3 -> new AdminDiscount(discounts).discountManagement();
			case 4 -> new AdminDeliveryAgents(deliveryAgents).deliveryManagement();
			case 5 -> {
				ObjectStore.save(MENU_FILE, menus);
				ObjectStore.save(DISCOUNT_FILE, discounts);
				ObjectStore.save(DELIVERY_FILE, deliveryAgents);
				System.out.println("Logged out.");
			}
			default -> System.out.println("Invalid option.");
			}
		} while (choice != 5);
	}
}
