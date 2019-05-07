package com.gym;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dal.CustomersDAO;
import com.model.impl.Customer;

public class CustomersDAOTest {

	@Test
	public void tesLoadCustomers() {
		
		CustomersDAO loadedCustomers= new CustomersDAO();
		
		List<Customer> customers = loadedCustomers.loadCustomers("./customers.txt");
		
		assertEquals(1, customers.size());
		assertEquals(1, customers.get(0).getCustomerId());
		assertEquals("Adi", customers.get(0).getName());
	}

	
	@Test
	public void tesSaveCustomers() {
		
		Customer c1 = new Customer(1, "Ana", "0000");
		Customer c2 = new Customer(1, "Ilie", "1111");
		List<Customer> customers= new ArrayList<>();
		customers.add(c1);
		customers.add(c2);
		
		CustomersDAO saveCustomers = new CustomersDAO();
		saveCustomers.saveClients("./clients-test.txt", customers);
		
		List<Customer> loadedCustomers = saveCustomers.loadCustomers("./clients-test.txt");
		
		assertEquals(customers.size() , loadedCustomers.size());
		assertEquals(customers.get(0).getName() , loadedCustomers.get(0).getName());
		
	}	
}