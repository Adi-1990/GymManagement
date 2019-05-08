package com.model.impl;

import java.time.LocalDate;

public abstract class SubscriptionToHall {

	private int subcriptionId;
	private Customer customer;
	private LocalDate startDate;
	private LocalDate period;
	private TypeOfSubscriptions typeOfSubscription;
	private double price;

	public SubscriptionToHall(int subcriptionId, Customer customer, LocalDate startDate, LocalDate period,
			TypeOfSubscriptions typeOfSubscription) {
		super();
		this.subcriptionId = subcriptionId;
		this.customer = customer;
		this.startDate = startDate;
		this.period = period;
		this.typeOfSubscription = typeOfSubscription;
//		this.price = price;
	}

	public int getSubcriptionId() {
		return subcriptionId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getPeriod() {
		return period;
	}

	public TypeOfSubscriptions getTypeOfSubscription() {
		return typeOfSubscription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {

		StringBuilder line = new StringBuilder();
		line.append(subcriptionId)
		.append(",")
		.append(customer.getCustomerId())
		.append(",")
		.append(startDate)
		.append(",")
		.append(period);

		if (typeOfSubscription == TypeOfSubscriptions.MORNING) {
			line.append("M");
		} else if (typeOfSubscription == TypeOfSubscriptions.FULL_DAY) {
			line.append("F");
		} else {
			line.append("E");
		}
		line.append(",")
		.append(price);

		return line.toString();
	}
}