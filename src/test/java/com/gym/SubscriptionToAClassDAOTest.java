package com.gym;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dal.CustomersDAO;
import com.dal.EquipmentsDAO;
import com.dal.ExercisePlansDAO;
import com.dal.SubscriptionsToAClassDAO;
import com.dal.TrainersDAO;
import com.model.impl.Customer;
import com.model.impl.Equipment;
import com.model.impl.ExercisePlan;
import com.model.impl.SubscriptionToAClass;
import com.model.impl.Trainer;

public class SubscriptionToAClassDAOTest {

	@Test
	public void testLoadSubscriptionsToAClass() {

		CustomersDAO customerDAO = new CustomersDAO();
		List<Customer> loadedCustomers = customerDAO.loadCustomers("./customers.txt");

		TrainersDAO trainersDAO = new TrainersDAO();
		List<Trainer> loadedTrainers = trainersDAO.loadTrainers("./trainers.txt");

		EquipmentsDAO equipmentDAO = new EquipmentsDAO();
		List<Equipment> loadedEquipments = equipmentDAO.loadEquipments("./equipments.txt");

		ExercisePlansDAO exercisePlanDAO = new ExercisePlansDAO();
		List<ExercisePlan> loadedExercisePlans = exercisePlanDAO.loadExercisePLans("./exercisePlans.txt",
				loadedTrainers, loadedEquipments);

		SubscriptionsToAClassDAO subscriptionToAClassDOAClassDAO = new SubscriptionsToAClassDAO();
		List<SubscriptionToAClass> subs = subscriptionToAClassDOAClassDAO.loadSubscriptionsToAClass(
				"./subscriptionToAClass.txt", loadedCustomers, loadedTrainers, loadedExercisePlans, loadedEquipments);

		assertEquals(2, subs.size());
		assertEquals(2, subs.get(1).getCustomer().getCustomerId());
	}

	@Test
	public void testSaveSubscriptionToAClass() {

		CustomersDAO customerDAO = new CustomersDAO();
		List<Customer> loadedCustomers = customerDAO.loadCustomers("./customers.txt");

		TrainersDAO trainersDAO = new TrainersDAO();
		List<Trainer> loadedTrainers = trainersDAO.loadTrainers("./trainers.txt");

		EquipmentsDAO equipmentDAO = new EquipmentsDAO();
		List<Equipment> loadedEquipments = equipmentDAO.loadEquipments("./equipments.txt");

		ExercisePlansDAO exercisePlanDAO = new ExercisePlansDAO();
		List<ExercisePlan> loadedExercisePlans = exercisePlanDAO.loadExercisePLans("./exercisePlans.txt",
				loadedTrainers, loadedEquipments);

		SubscriptionsToAClassDAO dao = new SubscriptionsToAClassDAO();
		List<SubscriptionToAClass> subsc = dao.loadSubscriptionsToAClass("./subscriptionToAClass.txt", loadedCustomers,
				loadedTrainers, loadedExercisePlans, loadedEquipments);

		int pos = 0;
		int pos1 = 1;

		SubscriptionToAClass a1 = new SubscriptionToAClass(subsc.get(pos).getSubscriptionToClassID(),
				loadedCustomers.get(pos), loadedTrainers.get(pos), loadedExercisePlans.get(pos),
				loadedEquipments.get(pos));
		SubscriptionToAClass a2 = new SubscriptionToAClass(subsc.get(pos1).getSubscriptionToClassID(),
				loadedCustomers.get(pos1), loadedTrainers.get(pos1), loadedExercisePlans.get(pos1),
				loadedEquipments.get(pos1));

		List<SubscriptionToAClass> classSubscriptions = new ArrayList<>();
		classSubscriptions.add(a1);
		classSubscriptions.add(a2);

		SubscriptionsToAClassDAO dao1 = new SubscriptionsToAClassDAO();

		dao1.saveSubscriptionToAClass("./subscriptionToAClass-Test.txt", classSubscriptions);

		List<SubscriptionToAClass> subscription2 = dao1.loadSubscriptionsToAClass("./subscriptionToAClass-Test.txt",
				loadedCustomers, loadedTrainers, loadedExercisePlans, loadedEquipments);
		
		assertEquals(subscription2.size(), classSubscriptions.size());
		assertEquals(subscription2.get(pos).getCustomer(), classSubscriptions.get(pos).getCustomer());
		assertEquals(subscription2.get(pos).getSubscriptionToClassID(), classSubscriptions.get(pos).getSubscriptionToClassID());

	}

}
