package com.tss.menus;

import java.util.ArrayList;
import java.util.List;

public class ItalianMenu implements IMenu {

	private static final long serialVersionUID = 1L;
	private List<FoodItem> items = new ArrayList<>();

	@Override
	public List<FoodItem> getMenuItems() {
		return items;
	}

}
