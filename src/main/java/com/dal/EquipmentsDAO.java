package com.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.model.impl.Equipment;

public class EquipmentsDAO {

	public List<Equipment> loadEquipments(String path) {

		Path filePath = Paths.get(path);

		try {
			List<String> content = Files.readAllLines(filePath);
			List<Equipment> equipments = parseContent(content);
			return equipments;

		} catch (IOException e) {
			System.err.println("The file with equipments could not be found");
			return Collections.emptyList();
		}
	}

	private List<Equipment> parseContent(List<String> allLines) {
		List<Equipment> equipments = new ArrayList<>();

		for (String line : allLines) {
			String[] values = line.split(",");
			int equipmentId = Integer.parseInt(values[0]);
			String name = values[1];
			equipments.add(new Equipment(equipmentId, name));
		}
		return equipments;
	}

	public void saveEquipments(String path, List<Equipment> equipments) {

		Path filePath = Paths.get(path);

		List<String> content = new ArrayList<String>();

		for (Equipment e : equipments) {
			content.add(e.toString());
		}
		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of equipments could not be saved on disk!");
		}
	}
}
