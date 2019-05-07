package com.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.model.impl.Trainer;

public class TrainersDAO {

	public List<Trainer> loadTrainers(String path) {

		Path filePath = Paths.get(path);
		try {
			List<String> content = Files.readAllLines(filePath);
			List<Trainer> trainers = parseContent(content);
			return trainers;
		} catch (IOException e) {
			System.err.println("The file with trainers could not be found");
			return Collections.emptyList();
		}
	}

	private List<Trainer> parseContent(List<String> allLines) {
		List<Trainer> trainers = new ArrayList<>();

		for (String line : allLines) {
			String[] values = line.split(",");
			int customerId = Integer.parseInt(values[0]);
			String name = values[1];
			trainers.add(new Trainer(customerId, name));
		}
		return trainers;
	}

	public void saveTrainers(String path, List<Trainer> trainers) {

		Path filePath = Paths.get(path);

		List<String> content = new ArrayList<String>();

		for (Trainer c : trainers) {
			content.add(c.toString());
		}
		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of trainers could not be saved on disk!");
		}
	}
}
