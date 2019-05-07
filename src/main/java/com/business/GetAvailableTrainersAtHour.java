package com.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dal.EquipmentsDAO;
import com.dal.ExercisePlansDAO;
import com.dal.TrainersDAO;
import com.model.impl.Equipment;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;

public class GetAvailableTrainersAtHour {

	public List<Trainer> getAvailableTrainersAtHour(LocalTime hour) {

		TrainersDAO trainersDAO = new TrainersDAO();
		List<Trainer> loadedTrainers = trainersDAO.loadTrainers("./trainers.txt");

		EquipmentsDAO loadEquipments = new EquipmentsDAO();
		List<Equipment> loadedEquipments = loadEquipments.loadEquipments("./equipments.txt");

		ExercisePlansDAO loadExercisePlans = new ExercisePlansDAO();
		List<ExercisePlan> loadedExercisePlans = loadExercisePlans.loadExercisePLans("./exercisePlans.txt",
				loadedTrainers, loadedEquipments);

		List<Trainer> availableTrainersAHour = getAvailableTrainersAtHour(hour, loadedTrainers, loadedExercisePlans);

		return availableTrainersAHour;

	}

	private List<Trainer> getAvailableTrainersAtHour(LocalTime hour, List<Trainer> availableTrainersAtDay,
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

					if (!(hour.isAfter(hourStartClass) && hour.isBefore(hourFinishClass))) {
						allTrainersAvailable.add(t);
					}
				}
		}
		return allTrainersAvailable;
	}

}
