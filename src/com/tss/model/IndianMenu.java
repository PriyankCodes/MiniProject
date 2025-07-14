package com.tss.model;

import java.util.ArrayList;
import java.util.List;

public class IndianMenu implements IMenu {

	private List<FoodItem> items = new ArrayList<>();

	@Override
	public List<FoodItem> getMenuItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Indian Menu";
	}

}
