package com.customerTransaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customerTransaction.model.CustomerTransactionRequest;
import com.customerTransaction.model.CustomerTransactionResponse;
import com.customerTransaction.service.CustomerTransactionService;

@RestController
public class CustomerTransactionController {
	
	@Autowired
	private CustomerTransactionService customerTransactionService;

	@RequestMapping(value="/getPointsForCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerTransactionResponse> getPointsForCustomer(@RequestBody(required = true) List<CustomerTransactionRequest> customerTransactionRequestList) throws Exception {
		return customerTransactionService.getPointsForCustomer(customerTransactionRequestList);
	}
}
