package com.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.model.impl.Equipment;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;
import com.model.impl.exercisePlan.Ciclyng;
import com.model.impl.exercisePlan.FunctionalTraining;
import com.model.impl.exercisePlan.PersonalTraining;

public class ExercisePlansDAO {

	public List<ExercisePlan> loadExercisePLans(String path, List<Trainer> trainers, List<Equipment> equipments) {

		Path filePath = Paths.get(path);
		try {
			List<String> content = Files.readAllLines(filePath);
			List<ExercisePlan> exercisePlans = parseContent(content, trainers, equipments);
			return exercisePlans;

		} catch (IOException e) {
			System.err.println("The file with exercise plans could not be found");
			return Collections.emptyList();
		}
	}

	private List<ExercisePlan> parseContent(List<String> allLines, List<Trainer> trainers, List<Equipment> equipments) {
		List<ExercisePlan> customers = new ArrayList<>();

		for (String line : allLines) {
			String[] values = line.split(",");

			int planId = Integer.parseInt(values[0]);

			int classesId = Integer.parseInt(values[1]);

			int trainerId = Integer.parseInt(values[2]);
			Trainer trainer = findTrainerById(trainerId, trainers);

			int equipmentId = Integer.parseInt(values[3]);
			Equipment equipment = findEquipmentById(equipmentId, equipments);

			LocalDate day = LocalDate.parse(values[4], DateTimeFormatter.ISO_DATE);
			LocalTime startClass = LocalTime.parse(values[5], DateTimeFormatter.ISO_LOCAL_TIME);
			LocalTime endClass = LocalTime.parse(values[6], DateTimeFormatter.ISO_LOCAL_TIME);
			int maxNoOfParticipants = Integer.parseInt(values[7]);

			switch (classesId) {
			case 1:
				customers.add(new Ciclyng(planId, classesId, trainer, equipment, day, startClass, endClass,
						maxNoOfParticipants));
				break;
			case 2:
				customers.add(new FunctionalTraining(planId, classesId, trainer, equipment, day, startClass, endClass,
						maxNoOfParticipants));
				break;
			case 3:
				customers.add(new PersonalTraining(planId, classesId, trainer, equipment, day, startClass, endClass,
						maxNoOfParticipants));
				break;
			default:
				throw new RuntimeException("Could not find trainer with Id : " + trainerId);
			}
		}
		return customers;
	}

	private Equipment findEquipmentById(int equipmentId, List<Equipment> equipments) {

		for (Equipment e : equipments)
			if (equipmentId == e.getEquipmentId())
				return e;
		throw new RuntimeException("Could not find equipment with ID: " + equipmentId);
	}

	private Trainer findTrainerById(int trainerId, List<Trainer> trainers) {

		for (Trainer t : trainers) {
			if (trainerId == t.getTrainerId())
				return t;
		}
		throw new RuntimeException("Could not find trainer with ID: " + trainerId);
	}

	public void saveClients(String path, List<ExercisePlan> classes) {

		Path filePath = Paths.get(path);
		List<String> content = new ArrayList<String>();
		for (ExercisePlan c : classes) {
			content.add(c.toString());
		}
		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of exercise plans could not be saved on disk!");
		}
	}
}
