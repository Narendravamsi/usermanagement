package com.user.management.user.model;

import java.math.BigDecimal;

public class EmailIdPayment {

	public int paymentId;

	public int orderId;

	public BigDecimal orderCost;

	public String paymentStatus;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(BigDecimal orderCost) {
		this.orderCost = orderCost;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
	

}
