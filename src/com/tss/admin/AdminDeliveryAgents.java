package com.tss.admin;

import java.util.List;
import java.util.Scanner;

import com.tss.deliveryagents.DeliveryAgentFactory;
import com.tss.deliveryagents.IDeliveryAgents;
import com.tss.util.ObjectStore;

public class AdminDeliveryAgents {
	private final List<IDeliveryAgents> deliveryAgents;
	private final String DELIVERY_FILE = "delivery.ser";
	private final Scanner scanner = new Scanner(System.in);

	public AdminDeliveryAgents(List<IDeliveryAgents> deliveryAgents) {
		this.deliveryAgents = deliveryAgents;
	}

	public void deliveryManagement() {
		int choice;
		do {
			System.out.println("\n--- Delivery Agent Management ---");
			System.out.println("1. Add Delivery Agent");
			System.out.println("2. Remove Delivery Agent");
			System.out.println("3. View Delivery Agents");
			System.out.println("4. Back");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1 -> addAgent();
			case 2 -> removeAgent();
			case 3 -> viewAgents();
			case 4 -> System.out.println("Returning to Admin menu.");
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 4);
	}

	private void addAgent() {
		System.out.print("Enter agent type (Swiggy/Zomato): ");
		String type = scanner.next();

		try {
			boolean alreadyAdded = deliveryAgents.stream()
					.anyMatch(agent -> agent.getClass().getSimpleName().equalsIgnoreCase(type));

			if (alreadyAdded) {
				System.out.println(type + " agent already added.");
				return;
			}

			IDeliveryAgents agent = DeliveryAgentFactory.createAgent(type);
			deliveryAgents.add(agent);
			ObjectStore.save(DELIVERY_FILE, deliveryAgents);
			System.out.println(agent.getClass().getSimpleName() + " added.");

		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
	}

	private void removeAgent() {
		if (deliveryAgents.isEmpty()) {
			System.out.println("No agents to remove.");
			return;
		}
		for (int i = 0; i < deliveryAgents.size(); i++) {
			System.out.println((i + 1) + ". " + deliveryAgents.get(i).getClass().getSimpleName());
		}
		System.out.print("Select to remove: ");
		int index = scanner.nextInt();
		if (index < 1 || index > deliveryAgents.size()) {
			System.out.println("Invalid index.");
			return;
		}
		deliveryAgents.remove(index - 1);
		ObjectStore.save(DELIVERY_FILE, deliveryAgents);
		System.out.println("Agent removed.");
	}

	private void viewAgents() {
		if (deliveryAgents.isEmpty()) {
			System.out.println("No agents available.");
			return;
		}
		for (int i = 0; i < deliveryAgents.size(); i++) {
			System.out.println((i + 1) + ". " + deliveryAgents.get(i).getClass().getSimpleName());
		}
	}
}
