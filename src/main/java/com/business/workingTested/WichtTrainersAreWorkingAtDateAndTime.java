package com.business.workingTested;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.dal.TrainerScheduleDAO;
import com.dal.TrainersDAO;

import com.model.impl.Trainer;
import com.model.impl.TrainerSchedule;

public class WichtTrainersAreWorkingAtDateAndTime {

	public List<Trainer> getAvailableTrainersAtDate(LocalDate day, LocalTime hour) {

		TrainersDAO trainersDAO = new TrainersDAO();
		List<Trainer> loadedTrainers = trainersDAO.loadTrainers("./trainers.txt");

		TrainerScheduleDAO trainerScheduleDAO = new TrainerScheduleDAO();
		List<TrainerSchedule> loadedTrainerSchedule = trainerScheduleDAO.loadTrainerScheudle("./trainerSchedule.txt",
				loadedTrainers);

		WichtTrainersAreWorkingAtDate trainersThatWorksAtDate = new WichtTrainersAreWorkingAtDate();
		List<Trainer> availableTrainersAtDate = trainersThatWorksAtDate.getAvailableTrainersAtDate(day);

		List<Trainer> availableTrainersAtDateAndHour = getAvailableTrainersAtHour(day, hour, loadedTrainerSchedule,
				availableTrainersAtDate);
		
//  remove duplicates from an ArrayList  
		
		// Create a new LinkedHashSet 
			Set<Trainer> set = new LinkedHashSet<>(); 
		// Add the elements to set 
			set.addAll(availableTrainersAtDateAndHour); 
		// Clear the list 
			availableTrainersAtDateAndHour.clear(); 
		// add the elements of set 
		// with no duplicates to the list 
			availableTrainersAtDateAndHour.addAll(set); ; 

		return availableTrainersAtDateAndHour;
	} 

	private List<Trainer> getAvailableTrainersAtHour(LocalDate day, LocalTime hour,
			List<TrainerSchedule> loadedTrainerSchedule, List<Trainer> loadedTrainers) {

		List<Trainer> trainers = new ArrayList<Trainer>();

		for (TrainerSchedule sc : loadedTrainerSchedule) {
			if (sc.getDay().equals(day) && sc.getStartHourOfWorkingDay() != null) {
				if (sc.getStartHourOfWorkingDay().minusSeconds(1).isBefore(hour)) {
					if (sc.getEndHourOfWorkingDay().plusSeconds(1).isAfter(hour)) {
						int trainerId = sc.getTrainerId();
						Trainer trainer = findTrainerById(trainerId, loadedTrainers);
						trainers.add(trainer);
					}
				}
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