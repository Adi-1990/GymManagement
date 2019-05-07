package com.gym;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dal.TrainersDAO;
import com.model.impl.Trainer;

public class TrainersDAOTest {

	@Test
	public void trainersLoadTest() {

		TrainersDAO dao = new TrainersDAO();
		List<Trainer> content = dao.loadTrainers("./trainers.txt");

		assertEquals(2, content.size());
		assertEquals(1, content.get(0).getTrainerId());
	}

	@Test
	public void trainersSaveTest() {

		Trainer trainer1 = new Trainer(1, "ilie");
		Trainer trainer2 = new Trainer(2, "gheo");

		List<Trainer> listOfTrainers = new ArrayList<Trainer>();
		listOfTrainers.add(trainer1);
		listOfTrainers.add(trainer2);

		TrainersDAO dao = new TrainersDAO();
		dao.saveTrainers("./trainers-test.txt", listOfTrainers);

		List<Trainer> trainers = dao.loadTrainers("./trainers-test.txt");

		assertEquals(trainers.size(), listOfTrainers.size());
		assertEquals(trainers.get(0).getName(), listOfTrainers.get(0).getName());

	}
}