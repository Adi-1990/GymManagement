package com.gym;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


import com.dal.TrainersDAO;
import com.model.impl.Trainer;
import com.dal.TrainerScheduleDAO;
import com.model.impl.TrainerSchedule;

public class TrainersScheduleDAOTest {

	@Test
	public void trainersScheduleLoadTest() {

		TrainersDAO trainerDao = new TrainersDAO();
		List<Trainer> listTrainers = trainerDao.loadTrainers("./trainers.txt");

		TrainerScheduleDAO dao = new TrainerScheduleDAO();
		List<TrainerSchedule> loadedTrainersSchedule = dao.loadTrainerScheudle("./trainerSchedule.txt", listTrainers);

		LocalTime ora = LocalTime.parse(("10:00"), DateTimeFormatter.ISO_LOCAL_TIME);
		LocalTime ora2 = LocalTime.parse(("10:00"), DateTimeFormatter.ISO_LOCAL_TIME);

		assertEquals(3, loadedTrainersSchedule.size());
		assertEquals(ora, loadedTrainersSchedule.get(0).getStartHourOfWorkingDay());
		assertEquals(ora2, loadedTrainersSchedule.get(1).getStartHourOfWorkingDay());
	}

	@Test
	public void trainersScheduleSaveTest() {
		
		TrainersDAO trainerDao1 = new TrainersDAO();
		List<Trainer> listTrainers1 = trainerDao1.loadTrainers("./trainers.txt");

		TrainerSchedule trainerSchedule = new TrainerSchedule(listTrainers1.get(0),(LocalDate.parse(("2019-05-02"), DateTimeFormatter.ISO_DATE)),(LocalTime.parse(("10:00"), DateTimeFormatter.ISO_LOCAL_TIME)), (LocalTime.parse(("18:00"), DateTimeFormatter.ISO_LOCAL_TIME)));
		TrainerSchedule trainerSchedule1 = new TrainerSchedule(listTrainers1.get(1),(LocalDate.parse(("2019-05-05"), DateTimeFormatter.ISO_DATE)),(LocalTime.parse(("10:00"), DateTimeFormatter.ISO_LOCAL_TIME)), (LocalTime.parse(("18:00"), DateTimeFormatter.ISO_LOCAL_TIME)));
		List<TrainerSchedule> listOfTrainersSchedule = new ArrayList<TrainerSchedule>();

		listOfTrainersSchedule.add(trainerSchedule);
		listOfTrainersSchedule.add(trainerSchedule1);

		TrainerScheduleDAO scheduleDao = new TrainerScheduleDAO();

		scheduleDao.saveTrainerSchedule("./trainers-schedule-test.txt", listOfTrainersSchedule);
		
		// 
				
		List<TrainerSchedule> trainerSchedule21 = scheduleDao.loadTrainerScheudle("./trainers-schedule-test.txt", listTrainers1);

		assertEquals(listOfTrainersSchedule.size(), trainerSchedule21.size());
		assertEquals(listOfTrainersSchedule.get(0).getTrainerId(), trainerSchedule21.get(0).getTrainerId());
	}
}