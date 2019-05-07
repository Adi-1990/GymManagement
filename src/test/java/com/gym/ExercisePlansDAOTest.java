package com.gym;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dal.EquipmentsDAO;
import com.dal.ExercisePlansDAO;
import com.dal.TrainersDAO;
import com.model.impl.Equipment;
import com.model.impl.ExercisePlan;
import com.model.impl.Trainer;
import com.model.impl.exercisePlan.PersonalTraining;

public class ExercisePlansDAOTest {

	@Test
	public void testLoadClasses() {

		ExercisePlansDAO loadExercisePlans = new ExercisePlansDAO();
		
		TrainersDAO loadTrainers = new TrainersDAO();
		List<Trainer> loadedTrainers = loadTrainers.loadTrainers("./trainers.txt");
		
		EquipmentsDAO loadEquipments = new EquipmentsDAO();
		List<Equipment> loadedEquipments = loadEquipments.loadEquipments("./equipments.txt");

		List<ExercisePlan> exercisePlans = loadExercisePlans.loadExercisePLans("./exercisePlans.txt", loadedTrainers, loadedEquipments);

		assertEquals(2, exercisePlans.size());
		assertEquals(1, exercisePlans.get(0).getPlanId());
		assertEquals(1, exercisePlans.get(0).getTrainerId());
		assertEquals(LocalTime.parse(("10:00"), DateTimeFormatter.ISO_LOCAL_TIME), exercisePlans.get(0).getStartClass());

	}

	@Test
	public void tesSaveCustomers() {
		
		
		LocalTime t1 = LocalTime.parse(("12:00"), DateTimeFormatter.ISO_LOCAL_TIME);
		LocalTime t2 = LocalTime.parse(("14:00"), DateTimeFormatter.ISO_LOCAL_TIME);
		LocalDate date = LocalDate.parse("2018-05-05", DateTimeFormatter.ISO_DATE);
		Trainer tr1 = new Trainer(1, "Adrian");
		Equipment e1 = new Equipment(1, "scripere");
		
		ExercisePlan c1 = new PersonalTraining(1, 1, tr1, e1, date, t1, t2, 5);
		List<ExercisePlan> exercisePlans = new ArrayList<>();
		exercisePlans.add(c1);
		
		ExercisePlansDAO dao = new ExercisePlansDAO();
		dao.saveClients("./exercisePlans-test.txt", exercisePlans);
		
		
		TrainersDAO loadTrainers = new TrainersDAO();
		List<Trainer> loadedTrainers = loadTrainers.loadTrainers("./trainers.txt");
		
		EquipmentsDAO loadEquipments = new EquipmentsDAO();
		List<Equipment> loadedEquipments = loadEquipments.loadEquipments("./equipments.txt");
		
		
		List<ExercisePlan> loadedExercisePlans = dao.loadExercisePLans("./exercisePlans-test.txt", loadedTrainers, loadedEquipments);
		
		assertEquals(exercisePlans.size(), loadedExercisePlans.size());
		assertEquals(exercisePlans.get(0).getDay(), loadedExercisePlans.get(0).getDay());
		assertEquals(exercisePlans.get(0).getMaxNoOfParticipants(), loadedExercisePlans.get(0).getMaxNoOfParticipants());
		
	}
}
