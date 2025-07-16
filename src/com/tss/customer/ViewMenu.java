package com.tss.customer;

import java.io.Serializable;
import java.util.List;

import com.tss.exception.NoMenuAvailableException;
import com.tss.model.FoodItem;
import com.tss.model.IMenu;

public class ViewMenu implements Serializable {

	private static final long serialVersionUID = 1L;
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
			System.out.println((i + 1) + ". " + menu.getClass().getSimpleName());
			for (FoodItem item : menu.getMenuItems()) {
				System.out.println("   - " + item);
			}
		}
	}

}
