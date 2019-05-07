package com.business.workingTested;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.dal.TrainerScheduleDAO;
import com.dal.TrainersDAO;

import com.model.impl.Trainer;
import com.model.impl.TrainerSchedule;

public class WichtTrainersAreWorkingAtDate {

	public List<Trainer> getAvailableTrainersAtDate(LocalDate day) {

		TrainersDAO trainersDAO = new TrainersDAO();
		List<Trainer> loadedTrainers = trainersDAO.loadTrainers("./trainers.txt");

		TrainerScheduleDAO trainerScheduleDAO = new TrainerScheduleDAO();
		List<TrainerSchedule> loadedTrainerSchedule = trainerScheduleDAO.loadTrainerScheudle("./trainerSchedule.txt",loadedTrainers);

		List<Trainer> availableTrainersAtDay = getAvailableTrainersAtDate(day, loadedTrainerSchedule, loadedTrainers);

//  remove duplicates from an ArrayList 
		
		// Create a new LinkedHashSet 
			Set<Trainer> set = new LinkedHashSet<>(); 
		// Add the elements to set 
			set.addAll(availableTrainersAtDay); 
		// Clear the list 
			availableTrainersAtDay.clear(); 
		// add the elements of set 
		// with no duplicates to the list 
			availableTrainersAtDay.addAll(set); 	
		
		return availableTrainersAtDay;
	}

	private List<Trainer> getAvailableTrainersAtDate(LocalDate day, List<TrainerSchedule> loadedTrainerSchedule, List<Trainer> loadedTrainers) {

		List<Trainer> trainers = new ArrayList<Trainer>();

		for (TrainerSchedule sc : loadedTrainerSchedule) {
			if (sc.getDay().equals(day) && sc.getStartHourOfWorkingDay() != null) {
				int trainerId = sc.getTrainerId();
				Trainer trainer = findTrainerById(trainerId, loadedTrainers);
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