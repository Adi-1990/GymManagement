package com.business;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dal.EquipmentsDAO;
import com.dal.ExercisePlansDAO;
import com.dal.TrainerScheduleDAO;
import com.dal.TrainersDAO;
import com.model.impl.Equipment;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;
import com.model.impl.TrainerSchedule;

public class getTrainersThatWorksAtDateAndTime2 {

	public List<Trainer> getAvailableTrainers(LocalDate day, LocalTime hour) {

		TrainersDAO trainersDAO = new TrainersDAO();
		List<Trainer> loadedTrainers = trainersDAO.loadTrainers("./trainers.txt");

		TrainerScheduleDAO trainerScheduleDAO = new TrainerScheduleDAO();
		List<TrainerSchedule> loadedTrainerSchedule = trainerScheduleDAO.loadTrainerScheudle("./trainerSchedule.txt",
				loadedTrainers);

		EquipmentsDAO loadEquipments = new EquipmentsDAO();
		List<Equipment> loadedEquipments = loadEquipments.loadEquipments("./equipments.txt");

		ExercisePlansDAO loadExercisePlans = new ExercisePlansDAO();
		List<ExercisePlan> exercisePlans = loadExercisePlans.loadExercisePLans("./exercisePlans.txt", loadedTrainers,
				loadedEquipments);

		List<Trainer> availableTrainersAtDay = getAvailableTrainersAtDay(day, loadedTrainerSchedule, loadedTrainers);
		List<Trainer> availableTrainerAtHour = getAvailableTrainersAHour(hour, availableTrainersAtDay, exercisePlans);

		return availableTrainerAtHour;
	}

	private List<Trainer> getAvailableTrainersAHour(LocalTime hour, List<Trainer> availableTrainersAtDay,
			List<ExercisePlan> exercisePlans) {

		List<Trainer> allTrainersAvailable = new ArrayList<Trainer>();

		for (Trainer t : availableTrainersAtDay) {
			for (ExercisePlan e : exercisePlans)
				if (t.getTrainerId() == e.getTrainerId()) {

					String duration = String.valueOf(e.getTime());
					String[] split = duration.split(":");
					long hours = Long.parseLong(split[0]);
					long minutes = Long.parseLong(split[1]);
					long durationInMinutes = hours * 60 + minutes;

					LocalTime hourStartClass = e.getStartClass();
					LocalTime hourFinishClass = e.getStartClass().plusMinutes(durationInMinutes);
					
					if (!(hour.isAfter(hourStartClass) && hour.isBefore(hourFinishClass ))) {
							allTrainersAvailable.add(t);
				}
			}
		}
		return allTrainersAvailable;
	}

	private List<Trainer> getAvailableTrainersAtDay(LocalDate day, List<TrainerSchedule> loadedTrainerSchedule, List<Trainer> loadedTrainers) {

		List<Trainer> trainers = new ArrayList<Trainer>();

		for (TrainerSchedule sc : loadedTrainerSchedule) {

			if (sc.getDay().equals(day) && sc.getStartHourOfWorkingDay() != null) {
				int Id = sc.getTrainerId();
				Trainer trainer = findTrainerById(Id, loadedTrainers);
				trainers.add(trainer);
			}
		}
		return trainers;
	}

	private Trainer findTrainerById(int id, List<Trainer> loadedTrainers) {
		for (Trainer t : loadedTrainers) {
			if (id == t.getTrainerId())
				return t;
		}
		throw new RuntimeException("Could not find trainer with Id : " + id);
	}
}
