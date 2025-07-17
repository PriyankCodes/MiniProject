package com.tss.customer;

import java.util.ArrayList;
import java.util.List;

import com.tss.util.ObjectLoad;
import com.tss.util.ObjectStore;
import com.tss.validate.InputValidator;

public class CustomerLogin {
	private static final String CUSTOMER_FILE = "customers.ser";

	public Customer authenticate() {

		List<Customer> customers = ObjectLoad.load(CUSTOMER_FILE);

		if (customers == null)
			customers = new ArrayList<>();

		String email = InputValidator.getValidEmail();

		for (Customer customer : customers) {
			if (customer.getEmail().equalsIgnoreCase(email)) {

				String password = InputValidator.getValidPassword();
				if (customer.getPassword().equals(password)) {
					System.out.println("Welcome back, " + customer.getName() + "!");
					return customer;
				} else {
					System.out.println("Incorrect password.");
					return null;
				}
			}
		}

		System.out.println("No account found. Let's create one.");

		String name = InputValidator.getValidName();

		String phone = InputValidator.getValidPhone();

		String address = InputValidator.getValidAddress();

		String password = InputValidator.getValidPassword();

		Customer newCustomer = new Customer(name, email, phone, address, password);
		customers.add(newCustomer);

		ObjectStore.save(CUSTOMER_FILE, customers);
		System.out.println("Account created successfully.");

		return newCustomer;
	}
}
