package com.tss.orders;

import java.util.Map;

import com.tss.customer.Customer;
import com.tss.model.FoodItem;

public class InvoicePrinter {

	public void printInvoice(Order order, Customer customer) {

		System.out.println("\n========= INVOICE =========");
		System.out.println("Order ID     : " + order.getOrderId());
		System.out.println("Customer ID  : " + customer.getCustomerId());
		System.out.println("Customer Name : " + customer.getName());
		System.out.println("Cutomer Phone : " + customer.getPhone());
		System.out.println("\nItems Ordered:");

		for (Map.Entry<FoodItem, Integer> entry : order.getItemQuantityMap().entrySet()) {
			System.out.println("  - " + entry.getKey() + " (x" + entry.getValue() + ")");
		}
		System.out.println();
		System.out.println("Total Amount     : ₹" + order.getTotalAmount());
		System.out.println("Discount Applied : ₹" + order.getDiscountApplied());
		System.out.println("Final Amount     : ₹" + order.getFinalAmount());

		String confirmation = order.getPayment().pay();
		System.out.println("Payment Status    : " + confirmation);

		if (order.getDeliveryPartner() != null) {
			System.out.println("Delivery Partner : " + order.getDeliveryPartner().getClass().getSimpleName());
		} else {
			System.out.println("Delivery Partner : Not assigned");
		}

		System.out.println("===========================\n");
	}
}
