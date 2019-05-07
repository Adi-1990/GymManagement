package com.BackUp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.business.getTrainersThatWorksAtDateAndTime;
import com.dal.EquipmentsDAO;
import com.dal.ExercisePlansDAO;
import com.dal.TrainersDAO;
import com.model.impl.Equipment;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;

public class Main {

	public static void main(String[] args) {

		getTrainersThatWorksAtDateAndTime aaa = new getTrainersThatWorksAtDateAndTime();
		List<Trainer> availableTrainers = aaa.getAvailableTrainers(LocalDate.parse("2019-05-05"),
				LocalTime.parse("13:51"));

		TrainersDAO loadTrainers = new TrainersDAO();
		List<Trainer> loadedTrainers = loadTrainers.loadTrainers("./trainers.txt");

		EquipmentsDAO loadEquipments = new EquipmentsDAO();
		List<Equipment> loadedEquipments = loadEquipments.loadEquipments("./equipments.txt");

		ExercisePlansDAO loadExercisePlans = new ExercisePlansDAO();
		List<ExercisePlan> exercisePlans = loadExercisePlans.loadExercisePLans("./exercisePlans.txt", loadedTrainers,
				loadedEquipments);

		for (Trainer tr : availableTrainers) {
			for (ExercisePlan ex : exercisePlans) {

				if (tr.getTrainerId() == ex.getTrainerId()) {
					System.out.println(ex.getPlanId() + " " + ex.getTrainerId() + " " + ex.getEquipmentId() + " "
							+ ex.getDay() + " " + ex.getStartClass() + " " + ex.getTime() + " "
							+ ex.getMaxNoOfParticipants() + " " + tr.getName());
				}
			}
		}

		for (Trainer t : availableTrainers) {
			System.out.println("trainer " + t.getName() + " Id: " + t.getTrainerId());
		}
	}
}
// set schedule for a trainer
// List the training plans for a trainer for a day & (a week, a month)
