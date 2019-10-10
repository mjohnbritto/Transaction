package com.bank.transaction.model;

import org.springframework.stereotype.Component;

@Component
public class TransactionDetails {
	private double amount;
	private String date;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
