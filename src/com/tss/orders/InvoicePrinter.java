package com.tss.orders;

import java.util.Map;

import com.tss.customer.Customer;
import com.tss.menus.FoodItem;

public class InvoicePrinter {

	public void printInvoice(Order order, Customer customer) {
		System.out.println("\n==========================================");
		System.out.println("                 INVOICE                  ");
		System.out.println("==========================================");
		System.out.printf("Order ID        : %s%n", order.getOrderId());
		System.out.printf("Customer ID     : %s%n", customer.getCustomerId());
		System.out.printf("Customer Name   : %s%n", customer.getName());
		System.out.printf("Customer Phone  : %s%n", customer.getPhone());

		System.out.println("\nItems Ordered:");
		System.out.println("--------------------------------------------------");
		System.out.printf("%-25s %8s %12s%n", "Item", "Qty", "Price (₹)");
		System.out.println("--------------------------------------------------");

		for (Map.Entry<FoodItem, Integer> entry : order.getItemQuantityMap().entrySet()) {
			FoodItem item = entry.getKey();
			int qty = entry.getValue();
			double price = item.getPrice() * qty;
			System.out.printf("%-25s %8d %12.2f%n", item.getName(), qty, price);
		}

		System.out.println("--------------------------------------------------");
		System.out.printf("%-25s %8s %12.2f%n", "", "Total", order.getTotalAmount());
		System.out.printf("%-25s %8s %12.2f%n", "", "Discount", order.getDiscountApplied());
		System.out.printf("%-25s %8s %12.2f%n", "", "Final", order.getFinalAmount());

		System.out.println("\nPayment Details:");
		System.out.println("--------------------------------------------------");
		System.out.printf("Payment Status  : %s%n", order.getPayment().pay());

		String deliveryPartner = (order.getDeliveryPartner() != null)
				? order.getDeliveryPartner().getClass().getSimpleName()
				: "Not Assigned";

		System.out.printf("Delivery Partner: %s%n", deliveryPartner);
		System.out.println("--------------------------------------------------");
		System.out.println("         Thank you for your order!               ");
		System.out.println("==========================================\n");
	}
}
