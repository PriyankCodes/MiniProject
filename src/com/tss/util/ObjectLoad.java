package com.tss.util;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ObjectLoad {

	public static <T> List<T> load(String filename) {

		try (ObjectInputStream object = new ObjectInputStream(new FileInputStream(filename))) {
			return (List<T>) object.readObject();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
