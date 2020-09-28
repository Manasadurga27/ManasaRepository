package com.customerTransaction.model;

import java.util.List;

public class CustomerTransactionRequest {
	
	private String customerName;
	private List<CustomerOrder> customerOrderList;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<CustomerOrder> getCustomerOrderList() {
		return customerOrderList;
	}
	public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
		this.customerOrderList = customerOrderList;
	}
}