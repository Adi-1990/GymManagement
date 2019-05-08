package com.model.impl.exercisePlan;

import java.time.LocalDate;
import java.time.LocalTime;

import com.model.impl.Equipment;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;

public class FunctionalTraining extends ExercisePlan {


	public FunctionalTraining(int planId, int classesId, Trainer trainerId, Equipment equipmentId, LocalDate day,
			LocalTime startClass, LocalTime time, int maxNoOfParticipants) {
		super(planId, classesId, trainerId, equipmentId, day, startClass, time, maxNoOfParticipants);
		
	}

	
	
//	@Override
//	public String toString() {
//	StringBuilder sb = new StringBuilder();
//	sb.append(getPlanId())
//	.append(",")
//	.append(getTrainerId())
//	.append(",")
//	.append(getEquipmentId())
//	.append(",")
//	.append(getDay())
//	.append(",")
//	.append(getStartClass())
//	.append(",")
//	.append(getTime())
//	.append(",")
//	.append(getMaxNoOfParticipants());
//	
//	return sb.toString();
//	
//	}


	@Override
	public String nameOfClass() {
		return "FunctionalTraining";
	}

}
