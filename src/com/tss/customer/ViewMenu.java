package com.tss.customer;

import java.util.List;

import com.tss.exception.NoMenuAvailableException;
import com.tss.menus.FoodItem;
import com.tss.menus.IMenu;

public class ViewMenu {

	private final List<IMenu> menus;

	public ViewMenu(List<IMenu> menus) {
		this.menus = menus;
	}

	public void showMenu() {
		if (menus == null || menus.isEmpty()) {
			throw new NoMenuAvailableException();

		}

		for (int i = 0; i < menus.size(); i++) {
			IMenu menu = menus.get(i);
			System.out.println("\n" + (i + 1) + ". " + menu.getClass().getSimpleName());
			for (FoodItem item : menu.getMenuItems()) {
				System.out.println("   - " + item);
			}
		}
	}

}
