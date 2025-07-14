package com.tss.model;

import java.util.Random;

public class InvoicePrinter {

	public void printInvoice(Order order) {
		System.out.println("\n========= INVOICE =========");
		System.out.println("Order ID     : " + order.getOrderId());
		System.out.println("Customer ID  : " + order.getCustomerId());
		System.out.println("Items Ordered:");
		for (FoodItem item : order.getOrderedItems()) {
			System.out.println("  - " + item);
		}
		System.out.println("Total Amount     : ₹" + order.getTotalAmount());
		System.out.println("Discount Applied : ₹" + order.getDiscountApplied());
		System.out.println("Final Amount     : ₹" + order.getFinalAmount());

		String[] companies = { "Swiggy", "Zomato" };
		String assignedCompany = companies[new Random().nextInt(companies.length)];
		System.out.println("Delivery Partner : " + assignedCompany);

		System.out.println("===========================\n");
	}
}
