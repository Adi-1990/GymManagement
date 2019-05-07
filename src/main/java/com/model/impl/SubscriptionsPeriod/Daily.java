package com.model.impl.SubscriptionsPeriod;

import java.time.LocalDate;

import com.model.impl.Customer;
import com.model.impl.SubscriptionToHall;
import com.model.impl.TypeOfSubscriptions;

public class Daily extends SubscriptionToHall {

	@Override
	public void setPrice(double price) {
		super.setPrice(15);
	}

	public Daily(int subcriptionId, Customer customer, LocalDate startDate, LocalDate period,
			TypeOfSubscriptions typeOfSubscription) {
		super(subcriptionId, customer, startDate, period, typeOfSubscription);
	}
}