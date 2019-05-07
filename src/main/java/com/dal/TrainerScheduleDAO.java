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

import com.model.impl.Trainer;
import com.model.impl.TrainerSchedule;

public class TrainerScheduleDAO {

	public List<TrainerSchedule> loadTrainerScheudle(String path, List<Trainer> trainers) {

		Path filePath = Paths.get(path);
		try {
			List<String> content = Files.readAllLines(filePath);
			List<TrainerSchedule> trainerSchedule = parseContent(content, trainers);
			return trainerSchedule;

		} catch (IOException e) {
			System.err.println("The file with trainers schedule could not be found");
			return Collections.emptyList();
		}
	}

	private List<TrainerSchedule> parseContent(List<String> allLines, List<Trainer> trainers) {
		List<TrainerSchedule> trainerSchedule = new ArrayList<>();

		for (String line : allLines) {
			String[] values = line.split(",");

			int trainerId = Integer.parseInt(values[0]);
			Trainer trainer = findTrainerById(trainerId, trainers);

			LocalDate date = LocalDate.parse(values[1], DateTimeFormatter.ISO_DATE);
			LocalTime StartHourOfWorkingDay = LocalTime.parse(values[2], DateTimeFormatter.ISO_LOCAL_TIME);
			LocalTime EndHourOfWorkingDay = LocalTime.parse(values[3], DateTimeFormatter.ISO_LOCAL_TIME);
			
			trainerSchedule.add(new TrainerSchedule(trainer, date, StartHourOfWorkingDay, EndHourOfWorkingDay));
		}
		return trainerSchedule;
	}

	private Trainer findTrainerById(int trainerId, List<Trainer> trainers) {
		for (Trainer t : trainers) {
			if (trainerId == t.getTrainerId())
				return t;
		}
		throw new RuntimeException("Could not find trainer with Id : " + trainerId);
	}

	public void saveTrainerSchedule(String path, List<TrainerSchedule> trainerSchedule) {

		Path filePath = Paths.get(path);
		List<String> content = new ArrayList<String>();

		for (TrainerSchedule t : trainerSchedule) {
			content.add(t.toString());
		}
		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of trainer schedules could not be saved on disk!");
		}
	}
}
