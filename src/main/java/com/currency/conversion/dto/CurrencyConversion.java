package com.currency.conversion.dto;

public final class CurrencyConversion {
	
	private final String fromCurrency;
	private final String toCurrency;
	private final Integer quantity;
	private final Double conversionRate;
	private final Double totalAmount;
	public CurrencyConversion(String fromCurrency, String toCurrency, Integer quantity, Double conversionRate,
			Double totalAmount) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.quantity = quantity;
		this.conversionRate = conversionRate;
		this.totalAmount = totalAmount;
	}
	public String getFromCurrency() {
		return fromCurrency;
	}
	public String getToCurrency() {
		return toCurrency;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Double getConversionRate() {
		return conversionRate;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
}
