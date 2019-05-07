package com.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.model.impl.Customer;
import com.model.impl.SubscriptionToAClass;
import com.model.impl.SubscriptionToHall;
import com.model.impl.TypeOfSubscriptions;
import com.model.impl.SubscriptionsPeriod.Daily;

public class SubscriptionsToHallDAO {

	public List<SubscriptionToHall> loadSubscriptionsToHall(String path, List<Customer> customers) {

		Path filePath = Paths.get(path);

		try {
			List<String> content = Files.readAllLines(filePath);
			List<SubscriptionToHall> hallSubscriptions = parseContent(content, customers);
			return hallSubscriptions;

		} catch (IOException e) {
			System.err.println("The file with hall subscriptions could not be found");
			return Collections.emptyList();
		}
	}

//	int subcriptionId		int customerId		
//	LocalDate startDate		LocalDate period	
//	String typeOfSubscription		double price

	private List<SubscriptionToHall> parseContent(List<String> allLines, List<Customer> customers) {

		List<SubscriptionToHall> hallSubscriptions = new ArrayList<>();

		for (String line : allLines) {

			String[] values = line.split(",");

			int subscriptionID = Integer.parseInt(values[0]);

			int customerId = Integer.parseInt(values[1]);
			Customer customer = findCustomerById(customerId, customers);

			LocalDate start = LocalDate.parse(values[2], DateTimeFormatter.ISO_DATE);
			LocalDate period;

			String periodSymbol = values[3];

			String subscriptionTypeSymbol = values[4];

//			double price2 = Double.parseDouble(values[4]);

			TypeOfSubscriptions subscriptionType = null;

			switch (subscriptionTypeSymbol) {
			case "M":
				subscriptionType = TypeOfSubscriptions.MORNING;
				break;
			case "F":
				subscriptionType = TypeOfSubscriptions.FULL_DAY;
				break;
			case "E":
				subscriptionType = TypeOfSubscriptions.EVENING;
				break;
			default:
				System.out.println("Period not found");
				break;
			}

			switch (periodSymbol) {
			case "D":
				period = start.plusDays(1);
				hallSubscriptions.add(new Daily(subscriptionID, customer, start, period, subscriptionType));
				break;
			case "w":
				period = start.plusWeeks(1);
				hallSubscriptions.add(new Daily(subscriptionID, customer, start, period, subscriptionType));
				break;
			case "M":
				period = start.plusMonths(1);
				hallSubscriptions.add(new Daily(subscriptionID, customer, start, period, subscriptionType));
				break;
			default:
				System.out.println("Period not found");
				break;
			}
		}
		return hallSubscriptions;
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
