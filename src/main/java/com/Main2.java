package com;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.business.workingTested.TrainerPlanForADate;
import com.business.workingTested.WichtTrainersAreWorkingAtDate;
import com.business.workingTested.WichtTrainersAreWorkingAtDateAndTime;
import com.business.workingTested.WichtTrainersAreWorkingAtIntervalOfDates;
import com.business.workingTested.WichtTrainersAreWorkingAtIntervalOfDatesAndHours;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;

public class Main2 {

	public static void main(String[] args) {

//		WichtTrainersAreWorkingAtDate getAvailableTrainersAtDate = new WichtTrainersAreWorkingAtDate();
//		List<Trainer> availableTrainersAtDate = getAvailableTrainersAtDate.getAvailableTrainersAtDate(LocalDate.parse("2019-05-05"));
//
//		for (Trainer e : availableTrainersAtDate) {
//			System.out.println(e.getTrainerId() + " " + e.getName());
//		}
//		
//		System.out.println("||");
//		
//		WichtTrainersAreWorkingAtDateAndTime sas = new WichtTrainersAreWorkingAtDateAndTime();
//		List<Trainer> availableTrainersAtDate2 = sas.getAvailableTrainersAtDate(LocalDate.parse("2019-05-05"),
//				LocalTime.parse("10:00"));
//
//		for (Trainer e : availableTrainersAtDate2) {
//			System.out.println(e.getTrainerId() + " " + e.getName());
//		}
//		
//		System.out.println("||");
//		
//		TrainerPlanForADate trainersAvailableAtDate = new TrainerPlanForADate();
//		LocalDate dateToCheck = LocalDate.parse("2019-05-05");
//		
//		List<ExercisePlan> trainerWorkingPlanAtADay = trainersAvailableAtDate.getTrainerWorkingPlanAtADay(dateToCheck, 3);
//		
//		for (ExercisePlan e : trainerWorkingPlanAtADay) {
//			System.out.println("For date " + dateToCheck + " trainer with ID no " + e.getTrainerId() + " is available! ");
//		}

//		LocalDate startDate = LocalDate.parse("2019-05-10");
//		LocalDate endDate = LocalDate.parse("2019-05-20");
//		
//		WichtTrainersAreWorkingAtIntervalOfDates getAvailableTrainersAtIntervalOfDates = new WichtTrainersAreWorkingAtIntervalOfDates();
//		List<Trainer> availableTrainersAIntervalOftDates = getAvailableTrainersAtIntervalOfDates.getAvailableTrainersAtDate(startDate, endDate);
//
//		for (Trainer e : availableTrainersAIntervalOftDates) {
//			System.err.println(e.getTrainerId() + " " + e.getName());
//		}
//		System.out.println("||");

		LocalDate startDate = LocalDate.parse("2019-05-04");
		LocalDate endDate = LocalDate.parse("2019-05-06");
		LocalTime startTime = LocalTime.parse("07:00");
		LocalTime endTime = LocalTime.parse("13:00");

		WichtTrainersAreWorkingAtIntervalOfDatesAndHours getAvailableTrainersAtIntervalOfDates = new WichtTrainersAreWorkingAtIntervalOfDatesAndHours();
		List<Trainer> availableTrainersAIntervalOftDates = getAvailableTrainersAtIntervalOfDates
				.getAvailableTrainersAtIntervatOfDatesAndHours(startDate, endDate, startTime, endTime);

		for (Trainer e : availableTrainersAIntervalOftDates) {
			System.err.println(e.getTrainerId() + " " + e.getName());
		}
//		System.out.println("||");

	}
}

// List all trainers working at specified date
// List all trainers working at specified date and hour
//List the program for a trainer for a date
// List all trainers working at specified interval of dates

// List all trainers working at specified interval of dates and hours
// List the program for a trainer for an interval of dates