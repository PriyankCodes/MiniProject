package com.tss.customer;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tss.deliveryagents.IDeliveryAgents;
import com.tss.exception.EmptyCartException;
import com.tss.orders.InvoicePrinter;
import com.tss.orders.Order;
import com.tss.payment.CardPayment;
import com.tss.payment.CashPayment;
import com.tss.payment.IPayment;
import com.tss.payment.UPIPayment;
import com.tss.util.ObjectLoad;
import com.tss.validate.ValidatePayment;

public class CustomerPayment {
	private static final String DELIVERY_FILE = "delivery.ser";

	private final Scanner scanner = new Scanner(System.in);
	private final Order currentOrder;
	private final List<Order> orders;
	private final Customer customer;

	public CustomerPayment(Order currentOrder, List<Order> orders, Customer customer) {
		this.currentOrder = currentOrder;
		this.orders = orders;
		this.customer = customer;
	}

	public void checkout() {
		if (currentOrder.getItemQuantityMap().isEmpty()) {
			throw new EmptyCartException();
		}

		System.out.println("\n===============================");
		System.out.println(" Checkout Process");
		System.out.println("===============================\n");

		payment(currentOrder);

		System.out.println("\nAssigning Delivery Agent...");
		List<IDeliveryAgents> partners = ObjectLoad.load(DELIVERY_FILE);

		if (partners == null || partners.isEmpty()) {
			System.out.println("No delivery agents available. Order will be processed without agent.");
		} else {
			IDeliveryAgents agent = partners.get(new Random().nextInt(partners.size()));
			currentOrder.setDeliveryPartner(agent);
			System.out.println("Delivery Partner Assigned: " + agent.getAgent());
		}

		orders.add(currentOrder);

		System.out.println("\nGenerating Invoice...");
		new InvoicePrinter().printInvoice(currentOrder, customer);
		System.out.println("Order placed successfully.\n");
	}

	private void payment(Order order) {
		System.out.println("\nPayment Section");
		System.out.println("-------------------------");
		System.out.println("1. UPI");
		System.out.println("2. Card");
		System.out.println("3. Cash");
		System.out.print("Enter your choice (1-3): ");

		int payChoice = scanner.nextInt();
		scanner.nextLine(); // consume leftover newline

		ValidatePayment valid = new ValidatePayment();
		IPayment payment;

		switch (payChoice) {
		case 1 -> {
			System.out.println("\nEnter UPI Details:");
			String upiId = valid.getValidUPI();
			String pin = valid.getValidPin();
			payment = new UPIPayment(upiId, pin, order.getFinalAmount());
			System.out.println("UPI Payment Successful.");
		}
		case 2 -> {
			System.out.println("\nEnter Card Details:");
			String cardNumber = valid.getValidCardNumber();
			String pin = valid.getValidPin();
			payment = new CardPayment(cardNumber, pin, order.getFinalAmount());
			System.out.println("Card Payment Successful.");
		}
		case 3 -> {
			payment = new CashPayment(order.getFinalAmount());
			System.out.println("Cash Payment Selected.");
		}
		default -> {
			System.out.println("Invalid payment method. Aborting checkout.");
			return;
		}
		}

		order.setPayment(payment);
	}
}
