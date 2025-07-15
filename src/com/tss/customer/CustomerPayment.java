package com.tss.customer;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tss.model.IDeliveryAgents;
import com.tss.model.IPayment;
import com.tss.orders.InvoicePrinter;
import com.tss.orders.Order;
import com.tss.payment.CardPayment;
import com.tss.payment.CashPayment;
import com.tss.payment.UPIPayment;
import com.tss.util.ObjectLoad;

public class CustomerPayment {
	private static final String DELIVERY_FILE = "delivery.ser";

	private final Scanner scanner = new Scanner(System.in);

	private Order currentOrder;
	private List<Order> orders;
	private Customer customer;

	public CustomerPayment(Order currentOrder, List<Order> orders, Customer customer) {
		super();
		this.currentOrder = currentOrder;
		this.orders = orders;
		this.customer = customer;
	}

	public void checkout() {
		if (currentOrder.getItemQuantityMap().isEmpty()) {
			System.out.println("Cart is empty. Add items first.");
			return;
		}

		payment(currentOrder);

		List<IDeliveryAgents> partners = ObjectLoad.load(DELIVERY_FILE);
		if (partners == null || partners.isEmpty()) {
			System.out.println("No delivery agents available.");
			return;
		}
		IDeliveryAgents partner = partners.get(new Random().nextInt(partners.size()));
		currentOrder.setDeliveryPartner(partner);

		orders.add(currentOrder);

		InvoicePrinter printer = new InvoicePrinter();
		printer.printInvoice(currentOrder,customer);

	}

	private void payment(Order order) {
		System.out.println("\nSelect Payment Method:");
		System.out.println("1. UPI");
		System.out.println("2. Card");
		System.out.println("3. Cash");
		System.out.print("Choice: ");
		int payChoice = scanner.nextInt();

		IPayment payment = null;
		switch (payChoice) {
		case 1 -> {
			System.out.print("Enter UPI ID: ");
			String upiId = scanner.next();

			System.out.print("Enter pin: ");
			String pin = scanner.next();
			payment = new UPIPayment(upiId, pin, order.getFinalAmount());
		}
		case 2 -> {
			System.out.print("Enter Card Number: ");
			String cardNumber = scanner.next();

			System.out.print("Enter pin: ");
			String pin = scanner.next();
			payment = new CardPayment(cardNumber, pin, order.getFinalAmount());
		}
		case 3 -> {
			payment = new CashPayment(order.getFinalAmount());
		}
		default -> {
			System.out.println("Invalid payment method. Aborting checkout.");
			return;
		}
		}
		order.setPayment(payment);
	}
}
