package com.currency.conversion.model;

public final class ConversionModel {
	
	private final String id;
	private final String from;
	private final String to;
	private final int quantity;
	private final Double rate;
	private final double totalAmount;

	public ConversionModel(String id, String from, String to, int quantity, double rate, double totalAmount) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.rate = rate;
		this.totalAmount=totalAmount;
	}

	public String getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getRate() {
		return rate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	
	
}
