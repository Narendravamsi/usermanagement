package com.user.management.user.model;

public class DeleteOrderByEmailId {
	
	private long OrderId;
	private String message;
	public DeleteOrderByEmailId(long orderId, String message) {
		this.OrderId = orderId;
		this.message = message;
	}
	public DeleteOrderByEmailId() {
		
	}
	public long getOrderId() {
		return OrderId;
	}
	public void setOrderId(long orderId) {
		OrderId = orderId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
