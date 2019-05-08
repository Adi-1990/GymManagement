package com.model.impl;

public class Customer {

	private int customerId;
	private String name;
	private String phoneNumber;

	public Customer(int customerId, String name, String phoneNumber) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {

		StringBuilder line = new StringBuilder();
		line.append(customerId)
		.append(",")
		.append(name)
		.append(",")
		.append(phoneNumber);

		return line.toString();
	}

}