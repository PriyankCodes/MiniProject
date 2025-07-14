package com.tss.model;

import java.io.Serializable;
import java.util.List;

public interface IMenu extends Serializable {
	List<FoodItem> getMenuItems();
}
