package com.tss.menus;

import java.io.Serializable;
import java.util.List;

public interface IMenu extends Serializable {
	List<FoodItem> getMenuItems();
}
