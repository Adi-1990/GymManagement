package com.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.model.impl.ExercisePlan;
import com.model.impl.Customer;
import com.model.impl.Equipment;
import com.model.impl.SubscriptionToAClass;
import com.model.impl.Trainer;

public class SubscriptionsToAClassDAO {

	public List<SubscriptionToAClass> loadSubscriptionsToAClass(String path, List<Customer> customers,
			List<Trainer> trainers, List<ExercisePlan> exercisePlans, List<Equipment> equipments) {

		Path filePath = Paths.get(path);

		try {
			List<String> content = Files.readAllLines(filePath);
			List<SubscriptionToAClass> classSubscriptions = parseContent(content, customers, trainers, exercisePlans,
					equipments);
			return classSubscriptions;

		} catch (IOException e) {
			System.err.println("The file with trainers could not be found");
			return Collections.emptyList();

		}
	}

	private List<SubscriptionToAClass> parseContent(List<String> allLines, List<Customer> customers,
			List<Trainer> trainers, List<ExercisePlan> classes, List<Equipment> equipments) {

		List<SubscriptionToAClass> classSubscriptions = new ArrayList<>();

		for (String line : allLines) {

			String[] values = line.split(",");

			int subscriptionToClassID = Integer.parseInt(values[0]);

			int customerId = Integer.parseInt(values[1]);
			Customer customer = findCustomerById(customerId, customers);

			int trainerId = Integer.parseInt(values[2]);
			Trainer trainer = findTrainerById(trainerId, trainers);

			int classesId = Integer.parseInt(values[3]);
			ExercisePlan classe = findClassesById(classesId, classes);

			int equipmentId = Integer.parseInt(values[4]);
			Equipment equipment = findEuipmentById(equipmentId, equipments);

			classSubscriptions
					.add(new SubscriptionToAClass(subscriptionToClassID, customer, trainer, classe, equipment));
		}

		return classSubscriptions;
	}

	private Equipment findEuipmentById(int equipmentId, List<Equipment> equipments) {
		for (Equipment e : equipments) {
			if (equipmentId == e.getEquipmentId())
				return e;
		}
		throw new RuntimeException("Could not find equipment with Id : " + equipmentId);
	}

	private ExercisePlan findClassesById(int classesId, List<ExercisePlan> classes) {
		for (ExercisePlan c : classes) {
			if (classesId == c.getPlanId())
				;
			return c;
		}
		throw new RuntimeException("Could not find class with Id : " + classesId);
	}

	private Trainer findTrainerById(int trainerId, List<Trainer> trainers) {
		for (Trainer t : trainers) {
			if (trainerId == t.getTrainerId())
				return t;
		}
		throw new RuntimeException("Could not find trainer with Id : " + trainerId);
	}

	private Customer findCustomerById(int customerId, List<Customer> customers) {
		for (Customer c : customers) {
			if (c.getCustomerId() == customerId)
				return c;
		}
		throw new RuntimeException("Could not find customer with Id : " + customerId);
	}

	public void saveSubscriptionToAClass(String path, List<SubscriptionToAClass> classSubscriptions) {

		Path filePath = Paths.get(path);

		List<String> content = new ArrayList<String>();

		// convert each client to its String representation
		for (SubscriptionToAClass c : classSubscriptions) {
			content.add(c.toString());
		}
		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of subscriptions of classes could not be saved on disk!");
		}
	}
}
