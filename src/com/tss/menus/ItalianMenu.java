package com.tss.menus;

import java.util.ArrayList;
import java.util.List;

import com.tss.model.FoodItem;
import com.tss.model.IMenu;

public class ItalianMenu implements IMenu {

	private static final long serialVersionUID = 1L;
	private List<FoodItem> items = new ArrayList<>();

	@Override
	public List<FoodItem> getMenuItems() {
		return items;
	}

}
