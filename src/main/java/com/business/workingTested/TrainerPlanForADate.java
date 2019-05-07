package com.business.workingTested;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.dal.EquipmentsDAO;
import com.dal.ExercisePlansDAO;
import com.dal.TrainersDAO;
import com.model.impl.Equipment;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;

public class TrainerPlanForADate {

	public List<ExercisePlan> getTrainerPlanForADate(LocalDate day, int trainerId) {

		TrainersDAO loadTrainers = new TrainersDAO();
		List<Trainer> loadedTrainers = loadTrainers.loadTrainers("./trainers.txt");

		EquipmentsDAO loadEquipments = new EquipmentsDAO();
		List<Equipment> loadedEquipments = loadEquipments.loadEquipments("./equipments.txt");

		ExercisePlansDAO loadExercisePlans = new ExercisePlansDAO();
		List<ExercisePlan> loadedExercisePlans = loadExercisePlans.loadExercisePLans("./exercisePlans.txt",
				loadedTrainers, loadedEquipments);

		List<ExercisePlan> exercisesPlan = getAvailableTrainersAtDate(day, trainerId, loadedExercisePlans);

		// remove duplicates from an ArrayList

		// Create a new LinkedHashSet
		Set<ExercisePlan> set = new LinkedHashSet<>();
		// Add the elements to set
		set.addAll(exercisesPlan);
		// Clear the list
		exercisesPlan.clear();
		// add the elements of set
		// with no duplicates to the list
		exercisesPlan.addAll(set);

		return exercisesPlan;
	}

	private List<ExercisePlan> getAvailableTrainersAtDate(LocalDate day, int trainerId,
			List<ExercisePlan> loadedExercisePlans) {

		List<ExercisePlan> exercisesPlan = new ArrayList<ExercisePlan>();

		for (ExercisePlan ex : loadedExercisePlans) {
//			if (((startDate.isAfter(ex.getDay())) && (endDate.isAfter(ex.getDay())))) {

			if ((day.isEqual(ex.getDay())) && ((day.isEqual(ex.getDay())))) {

				if (trainerId == ex.getTrainerId()) {
					int planId = ex.getPlanId();
					exercisesPlan.add(findExById(loadedExercisePlans, planId));
				}
			}
		}
//		if (exercisesPlan.isEmpty()) {
//			throw new RuntimeException("Could not find trainer with Id : " + trainerId);
//		}
		return exercisesPlan;
	}

	private ExercisePlan findExById(List<ExercisePlan> exercisePlans, int planId) {
		for (ExercisePlan c : exercisePlans) {
			int getPlanId = c.getPlanId();
			if (planId == getPlanId) {
				return c;
			}
		}
		throw new RuntimeException("Could not find class with Id : " + planId);
	}
}
