package com.hsbc.rbwm.codecamp.converters.beans;

import java.util.List;

public class ResponseBody {

	private String accountNumber;
	private Integer transactionCount;
	private List<Transaction> transactions;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getTransactionCount() {
		return transactionCount;
	}
	public void setTransactionCount(Integer transactionCount) {
		this.transactionCount = transactionCount;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}
