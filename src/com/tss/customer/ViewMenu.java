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
			System.out.printf("%-5s %-20s %-10s %-30s%n", "No.", "Item Name", "Price", "Description");
			System.out.println("---------------------------------------------------------------------");

			List<FoodItem> items = menu.getMenuItems();
			for (int j = 0; j < items.size(); j++) {
				FoodItem item = items.get(j);
				System.out.printf("%-5d %-20s %-10.2f %-30s%n", (j + 1), item.getName(), item.getPrice(),
						item.getDescription());
			}
		}
	}

}
