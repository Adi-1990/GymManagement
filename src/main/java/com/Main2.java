package com;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.business.workingTested.TrainerPlanForADate;
import com.business.workingTested.TrainerPlanForAnIntervalOfDates;
import com.business.workingTested.WichtTrainersAreWorkingAtDate;
import com.business.workingTested.WichtTrainersAreWorkingAtDateAndTime;
import com.business.workingTested.WichtTrainersAreWorkingAtIntervalOfDates;
import com.business.workingTested.WichtTrainersAreWorkingAtIntervalOfDatesAndHours;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;

public class Main2 {

	public static void main(String[] args) {

		// List all trainers working at specified date
		LocalDate date = LocalDate.parse("2019-05-05");
		WichtTrainersAreWorkingAtDate getAvailableTrainersAtDate = new WichtTrainersAreWorkingAtDate();
		List<Trainer> availableTrainersAtDate = getAvailableTrainersAtDate.getAvailableTrainersAtDate(date);

		for (Trainer e : availableTrainersAtDate) {
			System.out.println(e.getTrainerId() + " " + e.getName());
		}

		System.out.println("||");

		// List all trainers working at specified date and hour
		date = LocalDate.parse("2019-05-05");
		LocalTime time = LocalTime.parse("10:00");
		WichtTrainersAreWorkingAtDateAndTime sas = new WichtTrainersAreWorkingAtDateAndTime();
		List<Trainer> availableTrainersAtDateAndHour = sas.getAvailableTrainersAtDate(date, time);

		for (Trainer e : availableTrainersAtDateAndHour) {
			System.out.println(e.getTrainerId() + " " + e.getName());
		}

		System.out.println("||");

		// List the program for a trainer for a date
		LocalDate day = LocalDate.parse("2019-05-08");
		int trainerIdToCheck = 1;

		TrainerPlanForADate trainerPlanForDate = new TrainerPlanForADate();

		List<ExercisePlan> trainerWorkingPlanAtADate = trainerPlanForDate.getTrainerPlanForADate(day, trainerIdToCheck);

		System.out.println("For date " + day + " trainer with ID no " + trainerIdToCheck + " has the next classes: ");

		for (ExercisePlan e : trainerWorkingPlanAtADate) {
			System.out.println("Class ID: " + e.getClassesId() + " Plan ID: " + e.getPlanId() + " start hour: "
					+ e.getStartClass() + " duration of class: " + e.getTime());
		}

		// List the program for a trainer for an interval of dates
		LocalDate startDate = LocalDate.parse("2019-05-05");
		LocalDate endDate = LocalDate.parse("2019-05-08");
		trainerIdToCheck = 1;

		TrainerPlanForAnIntervalOfDates trainersAvailableAtDate = new TrainerPlanForAnIntervalOfDates();

		List<ExercisePlan> trainerWorkingPlanAtADay = trainersAvailableAtDate
				.getTrainerPlanForAnIntervalOfDates(startDate, endDate, trainerIdToCheck);

		System.out.println("Between dates " + startDate + " and " + endDate + " trainer with ID no " + trainerIdToCheck
				+ " has the next classes: ");

		for (ExercisePlan e : trainerWorkingPlanAtADay) {
			System.out.println("Class ID: " + e.getClassesId() + " Plan ID: " + e.getPlanId() + " start hour: "
					+ e.getStartClass() + " duration of class: " + e.getTime());
		}

		System.out.println("||");

		// List all trainers working at specified interval of dates
		startDate = LocalDate.parse("2019-05-10");
		endDate = LocalDate.parse("2019-05-20");

		WichtTrainersAreWorkingAtIntervalOfDates getAvailableTrainersAtIntervalOfDates = new WichtTrainersAreWorkingAtIntervalOfDates();
		List<Trainer> availableTrainersAIntervalOftDates = getAvailableTrainersAtIntervalOfDates
				.getAvailableTrainersAtDate(startDate, endDate);

		for (Trainer e : availableTrainersAIntervalOftDates) {
			System.err.println(e.getTrainerId() + " " + e.getName());
		}
		System.out.println("||");

		// List all trainers working at specified interval of dates and hours
		startDate = LocalDate.parse("2019-05-04");
		endDate = LocalDate.parse("2019-05-06");
		LocalTime startTime = LocalTime.parse("07:00");
		LocalTime endTime = LocalTime.parse("13:00");

		WichtTrainersAreWorkingAtIntervalOfDatesAndHours getAvailableTrainersAtIntervalOfDatesAndHours = new WichtTrainersAreWorkingAtIntervalOfDatesAndHours();
		List<Trainer> AvailableTrainersAtIntervalOfDatesAndHours = getAvailableTrainersAtIntervalOfDatesAndHours
				.getAvailableTrainersAtIntervatOfDatesAndHours(startDate, endDate, startTime, endTime);

		for (Trainer e : AvailableTrainersAtIntervalOfDatesAndHours) {
			System.err.println(e.getTrainerId() + " " + e.getName());
		}

		System.out.println("||");
	}
}
