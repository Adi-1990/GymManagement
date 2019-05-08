package com.model.impl;

public class SubscriptionToAClass {

	private int subscriptionToClassID;
	private Customer customerId;
	private Trainer trainerId;
	private ExercisePlan exercisePlansID;
	private Equipment equipment;

	public int getSubscriptionToClassID() {
		return subscriptionToClassID;
	}

	public Customer getCustomer() {
		return customerId;
	}

	public Trainer getTrainer() {
		return trainerId;
	}

	public ExercisePlan getClasses() {
		return exercisePlansID;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public SubscriptionToAClass(int subscriptionToClassID, Customer customer, Trainer trainer, ExercisePlan classes,
			Equipment equipment) {
		super();
		this.subscriptionToClassID = subscriptionToClassID;
		this.customerId = customer;
		this.trainerId = trainer;
		this.exercisePlansID = classes;
		this.equipment = equipment;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(subscriptionToClassID)
		.append(",")
		.append(customerId.getCustomerId())
		.append(",")
		.append(trainerId.getTrainerId())
		.append(",")
		.append(exercisePlansID.getPlanId())
		.append(",")
		.append(equipment.getEquipmentId());
		
		return sb.toString();
	}
}