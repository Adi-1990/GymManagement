package com.model.impl.SubscriptionsPeriod;

import java.time.LocalDate;

import com.model.impl.Customer;
import com.model.impl.SubscriptionToHall;
import com.model.impl.TypeOfSubscriptions;

public class Weekly extends SubscriptionToHall {

	@Override
	public void setPrice(double price) {

		super.setPrice(50);
	}

	public Weekly(int subcriptionId, Customer customer, LocalDate startDate, LocalDate period,
			TypeOfSubscriptions typeOfSubscription) {
		super(subcriptionId, customer, startDate, period, typeOfSubscription);

	}
}