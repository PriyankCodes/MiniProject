package com.tss.customer;

import java.util.List;

import com.tss.model.FoodItem;
import com.tss.model.IMenu;

public class ViewMenu {

	private final List<IMenu> menus;

	public ViewMenu(List<IMenu> menus) {
		this.menus = menus;
	}

	public void showMenu() {
		if (menus == null || menus.isEmpty()) {
			System.out.println("No menus available.");
			return;
		}

		for (int i = 0; i < menus.size(); i++) {
			IMenu menu = menus.get(i);
			System.out.println((i + 1) + ". " + menu.getClass().getSimpleName());
			for (FoodItem item : menu.getMenuItems()) {
				System.out.println("   - " + item);
			}
		}
	}

}
