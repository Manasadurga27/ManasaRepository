package com.customerTransaction.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.customerTransaction.model.CustomerOrder;
import com.customerTransaction.model.CustomerTransactionRequest;
import com.customerTransaction.model.CustomerTransactionResponse;

@Service
public class CustomerTransactionService {
	
	public static final String FIRST_MONTH = "firstMonth";
	public static final String SECOND_MONTH = "secondMonth";
	public static final String THIRD_MONTH = "thirdMonth";
	public static final String CURRENT_MONTH = "currentMonth";
	
	public List<CustomerTransactionResponse> getPointsForCustomer(List<CustomerTransactionRequest> customerTransactionRequestList) {
		List<CustomerTransactionResponse> customerTransactionResponseList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(customerTransactionRequestList)) {
			customerTransactionRequestList.stream().forEach(customerTransactionRequest -> {
				CustomerTransactionResponse customerTransactionResponse = new CustomerTransactionResponse();
				customerTransactionResponse.setCustomerName(customerTransactionRequest.getCustomerName());
				List<CustomerOrder> customerOrdersList = customerTransactionRequest.getCustomerOrderList();
				AtomicLong pointsForCustomerThreeMonths = new AtomicLong();
				pointsForCustomerThreeMonths.set(0L);
				AtomicLong pointsForCustomerFirstMonth = new AtomicLong();
				pointsForCustomerFirstMonth.set(0L);
				AtomicLong pointsForCustomerSecondMonth = new AtomicLong();
				pointsForCustomerSecondMonth.set(0L);
				AtomicLong pointsForCustomerThirdMonth = new AtomicLong();
				pointsForCustomerThirdMonth.set(0L);
				AtomicLong pointsForCustomerCurrentMonth = new AtomicLong();
				pointsForCustomerCurrentMonth.set(0L);
				customerOrdersList.stream().forEach(customerOrder -> {
					Calendar transactionCalendar = Calendar.getInstance();
					Calendar currentCalendar = Calendar.getInstance();
					Date transactionDate = getMonthDetails(customerOrder.getTransactionDate());
					int diffOfTransactionAndCurrentDate = 0;
					if(transactionDate != null) {
						transactionCalendar.setTime(transactionDate);
						diffOfTransactionAndCurrentDate = currentCalendar.get(Calendar.MONTH) - transactionCalendar.get(Calendar.MONTH);
						if(diffOfTransactionAndCurrentDate == 3) {
							customerOrder.setTransactionMonth(THIRD_MONTH);
						} else if(diffOfTransactionAndCurrentDate == 2) {
							customerOrder.setTransactionMonth(SECOND_MONTH);
						} else if(diffOfTransactionAndCurrentDate == 1) {
							customerOrder.setTransactionMonth(FIRST_MONTH);
						} else if(diffOfTransactionAndCurrentDate == 0) {
							customerOrder.setTransactionMonth(CURRENT_MONTH);
						}
					}
					if(!StringUtils.isEmpty(customerOrder.getTransactionMonth()) && customerOrder.getTransactionMonth().equalsIgnoreCase(FIRST_MONTH)) {
						pointsForCustomerFirstMonth.set(pointsForCustomerFirstMonth.get() + logicToFetchPoints(customerOrder));
					} else if(!StringUtils.isEmpty(customerOrder.getTransactionMonth()) && customerOrder.getTransactionMonth().equalsIgnoreCase(SECOND_MONTH)) {
						pointsForCustomerSecondMonth.set(pointsForCustomerSecondMonth.get() + logicToFetchPoints(customerOrder));
					} else if(!StringUtils.isEmpty(customerOrder.getTransactionMonth()) && customerOrder.getTransactionMonth().equalsIgnoreCase(THIRD_MONTH)) {
						pointsForCustomerThirdMonth.set(pointsForCustomerThirdMonth.get() + logicToFetchPoints(customerOrder));
					} else if(!StringUtils.isEmpty(customerOrder.getTransactionMonth()) && customerOrder.getTransactionMonth().equalsIgnoreCase(CURRENT_MONTH)) {
						pointsForCustomerCurrentMonth.set(pointsForCustomerCurrentMonth.get() + logicToFetchPoints(customerOrder));
					}
					if(diffOfTransactionAndCurrentDate <= 3 && diffOfTransactionAndCurrentDate >= 0) {
						pointsForCustomerThreeMonths.set(pointsForCustomerThreeMonths.get() + logicToFetchPoints(customerOrder));
					}
				});
				customerTransactionResponse.setPointsForThreeMonths(pointsForCustomerThreeMonths.get());
				customerTransactionResponse.setPointsForFirstMonth(pointsForCustomerFirstMonth.get());
				customerTransactionResponse.setPointsForSecondMonth(pointsForCustomerSecondMonth.get());
				customerTransactionResponse.setPointsForThirdMonth(pointsForCustomerThirdMonth.get());
				customerTransactionResponse.setPointsForCurrentMonth(pointsForCustomerCurrentMonth.get());
				customerTransactionResponseList.add(customerTransactionResponse);
			});
		}
		
		return customerTransactionResponseList;
	}
	
	public Long logicToFetchPoints(CustomerOrder customerOrder) {
		Long defaultPoints = 0L;
		if(customerOrder.getBillAmount() > 100) {
			Long diffOfAmountOverHundred = customerOrder.getBillAmount() - 100;
			Long diffAmountPointsForHundred = diffOfAmountOverHundred*2;
			return diffAmountPointsForHundred + 50L;
		} else if(customerOrder.getBillAmount() <= 100 && customerOrder.getBillAmount() > 50) {
			Long diffOfAmountOverFifty = customerOrder.getBillAmount() - 50;
			Long diffAmountPointsForFifty = diffOfAmountOverFifty*1;
			return diffAmountPointsForFifty;
		}
		return defaultPoints;
	}
	
	public Date getMonthDetails(String transactionDateStr) {
		Date transactionDate = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			transactionDate = formatter.parse(transactionDateStr);
		} catch(ParseException e) {
			
		}
		return transactionDate;
	}
}
