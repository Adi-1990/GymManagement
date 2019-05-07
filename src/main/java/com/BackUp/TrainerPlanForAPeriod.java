package com.BackUp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dal.EquipmentsDAO;
import com.dal.ExercisePlansDAO;
import com.dal.TrainersDAO;
import com.model.impl.Equipment;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;

public class TrainerPlanForAPeriod {

	public List<ExercisePlan> getTrainerWorkingPlanAtADay(LocalDate startPeriod, LocalDate endPeriod, int trainerId) {

		TrainersDAO loadTrainers = new TrainersDAO();
		List<Trainer> loadedTrainers = loadTrainers.loadTrainers("./trainers.txt");

		EquipmentsDAO loadEquipments = new EquipmentsDAO();
		List<Equipment> loadedEquipments = loadEquipments.loadEquipments("./equipments.txt");

		ExercisePlansDAO loadExercisePlans = new ExercisePlansDAO();
		List<ExercisePlan> exercisePlans = loadExercisePlans.loadExercisePLans("./exercisePlans.txt", loadedTrainers,
				loadedEquipments);

		List<ExercisePlan> exercisesPlans = exercisesPlans(exercisePlans, startPeriod, endPeriod, trainerId);

		return exercisesPlans;

//		Period between = Period.between(startPeriod, endPeriod);

	}

	private List<ExercisePlan> exercisesPlans(List<ExercisePlan> exercisePlans, LocalDate startPeriod,
			LocalDate endPeriod, int trainerId) {

		List<ExercisePlan> exercisesPlan = new ArrayList<>();

		for (ExercisePlan ex : exercisePlans) {
			int planId = -1;
			if (trainerId == ex.getTrainerId()) {
				planId = ex.getPlanId();
				if (startPeriod.isAfter(ex.getDay()) || startPeriod.isEqual(ex.getDay()) || startPeriod.isBefore(ex.getDay())) {
					if (endPeriod.isBefore(ex.getDay()) || endPeriod.isEqual(ex.getDay()) || endPeriod.isAfter(ex.getDay())) {
					}
				}
			}
			ExercisePlan e = findExById(planId, exercisePlans);
			exercisesPlan.add(e);
		}
		return exercisesPlan;
	}

	private ExercisePlan findExById(int planId, List<ExercisePlan> exercisePlans) {
		for (ExercisePlan e : exercisePlans) {
			if (planId == e.getPlanId())
				;
			return e;
		}
		throw new RuntimeException("Could not find ex. plan with Id : " + planId);
	}
}
