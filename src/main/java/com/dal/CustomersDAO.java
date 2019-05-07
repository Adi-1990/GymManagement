package com.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.model.impl.Customer;

public class CustomersDAO {

	public List<Customer> loadCustomers(String path) {

		Path filePath = Paths.get(path);

		try {

			List<String> content = Files.readAllLines(filePath);
			List<Customer> customers = parseContent(content);
			return customers;

		} catch (IOException e) {
			System.err.println("The file with customers could not be found");
			return Collections.emptyList();
		}
	}

	private List<Customer> parseContent(List<String> allLines) {
		List<Customer> customers = new ArrayList<Customer>();

		for (String line : allLines) {
			String[] values = line.split(",");
			int customerId = Integer.parseInt(values[0]);
			String name = values[1];
			String phoneNumber = values[2];

			customers.add(new Customer(customerId, name, phoneNumber));
		}
		return customers;
	}

	public void saveClients(String path, List<Customer> clients) {

		Path filePath = Paths.get(path);
		List<String> content = new ArrayList<String>();

		for (Customer c : clients) {
			content.add(c.toString());
		}
		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of customers could not be saved on disk!");
		}
	}
}
