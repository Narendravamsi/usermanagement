package com.user.management.users.service;

import java.util.List;

import com.user.management.user.model.DeleteUserDetails;
import com.user.management.user.model.EmailIdOrders;
import com.user.management.user.model.EmailIdPayment;
import com.user.management.user.model.OrderRequest;
import com.user.management.user.model.ResetPassword;
import com.user.management.user.model.UpdateUserDetails;
import com.user.management.user.model.UsersRequest;
import com.user.management.user.model.UsersResponse;

public interface UsersService {

	List<UsersResponse> loadAllUsersDetails();

	UsersResponse loadByUserId(String emaiId);

	String insertUserDetails(UsersRequest request);

	UpdateUserDetails updateUserDetails(UpdateUserDetails request, String emailId);

	DeleteUserDetails deleteUserDetails(String emailId);

	ResetPassword updateNewPassword(ResetPassword request, String emailId);

	List<EmailIdOrders> loadOrdersByEmailId(String emailId);


	UsersResponse getUser(String emailId);

	String insertOrderDetailsFromUser(OrderRequest request);
	
	List<EmailIdPayment> loadPaymentByEmailId(String emailId);

}
