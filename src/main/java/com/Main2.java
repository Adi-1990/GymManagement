package com;

import java.time.LocalDate;
import java.util.List;

import com.business.workingTested.TrainerPlanForADate;
import com.model.impl.ExercisePlan;

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
//		LocalDate startDate = LocalDate.parse("2019-05-05");
//		LocalDate endDate = LocalDate.parse("2019-05-08");	
//		int trainerIdToCheck = 1;
//		
//		TrainerPlanForAnIntervalOfDates trainersAvailableAtDate = new TrainerPlanForAnIntervalOfDates();
//
//		List<ExercisePlan> trainerWorkingPlanAtADay = trainersAvailableAtDate.getTrainerPlanForAnIntervalOfDates(startDate, endDate, trainerIdToCheck);
//
//		System.out.println(
//				"Between dates " + startDate + " and " + endDate + " trainer with ID no " + trainerIdToCheck + " has the next classes: ");
//
//		for (ExercisePlan e : trainerWorkingPlanAtADay) {
//			System.out.println("Class ID: " + e.getClassesId() + " Plan ID: " + e.getPlanId() + " start hour: "
//					+ e.getStartClass() + " duration of class: " + e.getTime());
//		}
		
//		System.out.println("||");

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

//		LocalDate startDate = LocalDate.parse("2019-05-04");
//		LocalDate endDate = LocalDate.parse("2019-05-06");
//		LocalTime startTime = LocalTime.parse("07:00");
//		LocalTime endTime = LocalTime.parse("13:00");
//
//		WichtTrainersAreWorkingAtIntervalOfDatesAndHours getAvailableTrainersAtIntervalOfDates = new WichtTrainersAreWorkingAtIntervalOfDatesAndHours();
//		List<Trainer> availableTrainersAIntervalOftDates = getAvailableTrainersAtIntervalOfDates
//				.getAvailableTrainersAtIntervatOfDatesAndHours(startDate, endDate, startTime, endTime);
//
//		for (Trainer e : availableTrainersAIntervalOftDates) {
//			System.err.println(e.getTrainerId() + " " + e.getName());
//		}
//		System.out.println("||");

		LocalDate day = LocalDate.parse("2019-05-08");
		int trainerIdToCheck = 1;
		
		TrainerPlanForADate trainersAvailableAtDate = new TrainerPlanForADate();

		List<ExercisePlan> trainerWorkingPlanAtADay = trainersAvailableAtDate.getTrainerPlanForADate(day, trainerIdToCheck);

		System.out.println(
				"For date " + day + " trainer with ID no " + trainerIdToCheck + " has the next classes: ");

		for (ExercisePlan e : trainerWorkingPlanAtADay) {
			System.out.println("Class ID: " + e.getClassesId() + " Plan ID: " + e.getPlanId() + " start hour: "
					+ e.getStartClass() + " duration of class: " + e.getTime());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

// List all trainers working at specified date
// List all trainers working at specified date and hour
// List the program for a trainer for a date
// List all trainers working at specified interval of dates
// List all trainers working at specified interval of dates and hours

// List the program for a trainer for an interval of dates