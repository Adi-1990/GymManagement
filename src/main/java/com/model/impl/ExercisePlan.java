package com.model.impl;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class ExercisePlan {

	private int planId;
	private int classesId;
	private int trainerId;
	private int equipmentId;
	private LocalDate day;
	private LocalTime startClass;
	private LocalTime time;	
	private int MaxNoOfParticipants;	

	public ExercisePlan(int planId, int classesId, Trainer trainerId, Equipment equipmentId, LocalDate day, LocalTime startClass, LocalTime time,
			int maxNoOfParticipants) {
		super();
		this.planId = planId;
		this.classesId = classesId;
		this.trainerId = trainerId.getTrainerId();
		this.equipmentId = equipmentId.getEquipmentId();
		this.day = day;
		this.startClass = startClass;
		this.time = time;
		this.MaxNoOfParticipants = maxNoOfParticipants;
	}
		
	public LocalTime getTime() {
		return time;
	}
	
	public int getClassesId() {
		return classesId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public int getPlanId() {
		return planId;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public LocalDate getDay() {
		return day;
	}

	public LocalTime getStartClass() {
		return startClass;
	}


	public int getMaxNoOfParticipants() {
		return MaxNoOfParticipants;
	}
	
	public abstract String nameOfClass();

	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(planId)
	.append(",")
	.append(classesId)
	.append(",")
	.append(trainerId)
	.append(",")
	.append(equipmentId)
	.append(",")
	.append(day)
	.append(",")
	.append(startClass)
	.append(",")
	.append(time)
	.append(",")
	.append(MaxNoOfParticipants);
	
	return sb.toString();
	
	}	
}
