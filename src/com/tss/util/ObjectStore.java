package com.tss.util;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.List;

public class ObjectStore {

	public static <T> void save(String filename, List<T> data) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(data);
		} catch (IOException e) {
			System.out.println("Error saving data to " + filename);
			e.printStackTrace();
		}
	}
}
