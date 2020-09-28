package com.customerTransaction.model;

public class CustomerTransactionResponse {

	private String customerName;
	private Long pointsForFirstMonth;
	private Long pointsForSecondMonth;
	private Long pointsForThirdMonth;
	private Long pointsForCurrentMonth;
	private Long pointsForThreeMonths;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getPointsForFirstMonth() {
		return pointsForFirstMonth;
	}
	public void setPointsForFirstMonth(Long pointsForFirstMonth) {
		this.pointsForFirstMonth = pointsForFirstMonth;
	}
	public Long getPointsForSecondMonth() {
		return pointsForSecondMonth;
	}
	public void setPointsForSecondMonth(Long pointsForSecondMonth) {
		this.pointsForSecondMonth = pointsForSecondMonth;
	}
	public Long getPointsForThirdMonth() {
		return pointsForThirdMonth;
	}
	public void setPointsForThirdMonth(Long pointsForThirdMonth) {
		this.pointsForThirdMonth = pointsForThirdMonth;
	}
	public Long getPointsForThreeMonths() {
		return pointsForThreeMonths;
	}
	public void setPointsForThreeMonths(Long pointsForThreeMonths) {
		this.pointsForThreeMonths = pointsForThreeMonths;
	}
	public Long getPointsForCurrentMonth() {
		return pointsForCurrentMonth;
	}
	public void setPointsForCurrentMonth(Long pointsForCurrentMonth) {
		this.pointsForCurrentMonth = pointsForCurrentMonth;
	}
}