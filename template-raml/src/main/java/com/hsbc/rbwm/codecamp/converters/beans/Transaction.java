package com.hsbc.rbwm.codecamp.converters.beans;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
	
	private String transactionId;
	private Date postedDate;
	private String description;
	private BigDecimal amount;
	private String menmonics;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getMenmonics() {
		return menmonics;
	}
	public void setMenmonics(String menmonics) {
		this.menmonics = menmonics;
	}
	
}
