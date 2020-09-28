package com.customerTransaction.model;

public class CustomerOrder {

	private String transactionNumber;
	private Long billAmount;
	private String transactionDate;
	private String transactionMonth;
	
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public Long getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Long billAmount) {
		this.billAmount = billAmount;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionMonth() {
		return transactionMonth;
	}
	public void setTransactionMonth(String transactionMonth) {
		this.transactionMonth = transactionMonth;
	}
}