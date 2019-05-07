package com.model.impl;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainerSchedule {

	private int trainerId;
	private LocalDate day;
	private LocalTime StartHourOfWorkingDay;
	private LocalTime EndHourOfWorkingDay;
//	private ExercisePlan exercisePlans;

	public TrainerSchedule(Trainer trainer, LocalDate day, LocalTime startHourOfWorkingDay, LocalTime endHourOfWorkingDay2) {
		super();
		this.trainerId = trainer.getTrainerId();
		this.day = day;
		this.StartHourOfWorkingDay = startHourOfWorkingDay;
		this.EndHourOfWorkingDay = startHourOfWorkingDay.plusHours((long) 8.0);
//		this.exercisePlans = classes;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public LocalDate getDay() {
		return day;
	}

	public LocalTime getStartHourOfWorkingDay() {
		return StartHourOfWorkingDay;
	}

	public LocalTime getEndHourOfWorkingDay() {
		return EndHourOfWorkingDay;
	}

//	public ExercisePlan getClasses() {
//		return exercisePlans;
//	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(trainerId)
//		.append(exercisePlans)
		.append(",")
		.append(day)
		.append(",")
		.append(StartHourOfWorkingDay)
		.append(",")
		.append(EndHourOfWorkingDay);

		return sb.toString();

	}
}
