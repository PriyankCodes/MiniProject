package com.tss.customer;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tss.exception.EmptyCartException;
import com.tss.model.IDeliveryAgents;
import com.tss.model.IPayment;
import com.tss.orders.InvoicePrinter;
import com.tss.orders.Order;
import com.tss.payment.CardPayment;
import com.tss.payment.CashPayment;
import com.tss.payment.UPIPayment;
import com.tss.util.ObjectLoad;
import com.tss.validate.ValidatePayment;

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
			throw new EmptyCartException();
		}

		payment(currentOrder);

		List<IDeliveryAgents> partners = ObjectLoad.load(DELIVERY_FILE);
		if (partners == null || partners.isEmpty()) {
			System.out.println("No delivery agents available.");
			
		}
		IDeliveryAgents partner = partners.get(new Random().nextInt(partners.size()));
		currentOrder.setDeliveryPartner(partner);

		orders.add(currentOrder);

		InvoicePrinter printer = new InvoicePrinter();
		printer.printInvoice(currentOrder, customer);


	}

	private void payment(Order order) {
		System.out.println("\nSelect Payment Method:");
		System.out.println("1. UPI");
		System.out.println("2. Card");
		System.out.println("3. Cash");
		System.out.print("Choice: ");
		int payChoice = scanner.nextInt();

		ValidatePayment valid = new ValidatePayment();
		IPayment payment = null;
		switch (payChoice) {
		case 1 -> {
			String upiId = valid.getValidUPI();
			String pin = valid.getValidPin();
			payment = new UPIPayment(upiId, pin, order.getFinalAmount());
		}
		case 2 -> {
			String cardNumber = valid.getValidCardNumber();
			String pin = valid.getValidPin();
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
