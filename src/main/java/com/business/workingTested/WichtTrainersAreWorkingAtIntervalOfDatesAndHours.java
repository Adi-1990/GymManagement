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

public class WichtTrainersAreWorkingAtIntervalOfDatesAndHours {

	public List<Trainer> getAvailableTrainersAtIntervatOfDatesAndHours(LocalDate startDate, LocalDate endDate,
			LocalTime startTime, LocalTime endTime) {

		TrainersDAO trainersDAO = new TrainersDAO();
		List<Trainer> loadedTrainers = trainersDAO.loadTrainers("./trainers.txt");

		TrainerScheduleDAO trainerScheduleDAO = new TrainerScheduleDAO();
		List<TrainerSchedule> loadedTrainerSchedule = trainerScheduleDAO.loadTrainerScheudle("./trainerSchedule.txt",
				loadedTrainers);

		List<Trainer> availableTrainersAIntervalOftDates = getAvailableTrainersAtIntervalOfDates(startDate,
				endDate, startTime, endTime, loadedTrainerSchedule, loadedTrainers);

//  remove duplicates from an ArrayList 

		// Create a new LinkedHashSet
		Set<Trainer> set = new LinkedHashSet<>();
		// Add the elements to set
		set.addAll(availableTrainersAIntervalOftDates);
		// Clear the list
		availableTrainersAIntervalOftDates.clear();
		// add the elements of set
		// with no duplicates to the list
		availableTrainersAIntervalOftDates.addAll(set);

		return availableTrainersAIntervalOftDates;
	}


	private List<Trainer> getAvailableTrainersAtIntervalOfDates(LocalDate startDate, LocalDate endDate,
			LocalTime startTime, LocalTime endTime, List<TrainerSchedule> loadedTrainerSchedule,
			List<Trainer> loadedTrainers) {

		List<Trainer> trainers = new ArrayList<Trainer>();

		for (TrainerSchedule sc : loadedTrainerSchedule) {
			if ((startDate.isBefore(sc.getDay()) || startDate.isEqual(sc.getDay())) && ((endDate.isAfter(sc.getDay())) || (endDate.isEqual(sc.getDay())))) {
				if ((startTime.isAfter(sc.getStartHourOfWorkingDay().minusSeconds(1)))	&& (endTime.isBefore(sc.getEndHourOfWorkingDay().plusSeconds(1)))) {				
				int trainerId = sc.getTrainerId();
				Trainer trainer = findTrainerById(trainerId, loadedTrainers);
				trainers.add(trainer);
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