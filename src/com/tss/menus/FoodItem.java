package com.tss.menus;

import java.io.Serializable;

public class FoodItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private double price;
	private String description;

	public FoodItem(int id, String name, double price, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public FoodItem() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return  name + " (Rs." + price + ") - " + description;
	}
}
