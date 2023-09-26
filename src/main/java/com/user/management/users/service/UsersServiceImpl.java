package com.user.management.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.user.management.entity.Users;
import com.user.management.user.model.DeleteUserDetails;
import com.user.management.user.model.EmailIdOrders;
import com.user.management.user.model.EmailIdPayment;
import com.user.management.user.model.OrderRequest;
import com.user.management.user.model.ResetPassword;
import com.user.management.user.model.UpdateUserDetails;
import com.user.management.user.model.UsersRequest;
import com.user.management.user.model.UsersResponse;
import com.user.management.users.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository repo;

	@Override
	public List<UsersResponse> loadAllUsersDetails() {

		List<UsersResponse> usersList = new ArrayList<>();

		List<Users> users = (List<Users>) repo.findAll();

		for (Users user : users) {
			UsersResponse userResponseDetails = new UsersResponse();

			userResponseDetails.setEmailId(user.getEmailId());
			userResponseDetails.setFirstName(user.getFirstName());
			userResponseDetails.setLastName(user.getLastName());
			userResponseDetails.setMobileNumber(user.getMobileNummber());
			userResponseDetails.setPassword(user.getPassword());
			usersList.add(userResponseDetails);

		}

		return usersList;
	}

	@Override
	public UsersResponse loadByUserId(String emaiId) {

		Optional<Users> data = repo.findById(emaiId);

		Users user = data.get();
		UsersResponse userResponseById = new UsersResponse();

		userResponseById.setEmailId(user.getEmailId());
		userResponseById.setFirstName(user.getFirstName());
		userResponseById.setLastName(user.getLastName());
		userResponseById.setPassword(user.getPassword());
		userResponseById.setMobileNumber(user.getMobileNummber());

		return userResponseById;
	}

	@Override
	public String insertUserDetails(UsersRequest request) {

		Users users = new Users();

		users.setEmailId(request.getEmailId());
		users.setFirstName(request.getFirstName());
		users.setLastName(request.getLastName());
		users.setMobileNummber(request.getMobileNumber());
		users.setPassword(request.getPassword());

		Users userSave = repo.save(users);

		UsersRequest userRequest = new UsersRequest();

		userRequest.setEmailId(userSave.getEmailId());
		userRequest.setFirstName(userSave.getFirstName());
		userRequest.setLastName(userSave.getLastName());
		userRequest.setMobileNumber(userSave.getMobileNummber());
		userRequest.setPassword(userSave.getPassword());

		return "your user account is created";
	}

	@Override
	public UpdateUserDetails updateUserDetails(UpdateUserDetails request, String emailId) {

		Optional<Users> data = repo.findById(emailId);

		Users user = data.get();

		user.setFirstName(request.getFirstName());
		user.setEmailId(emailId);

		Users users = repo.save(user);

		UpdateUserDetails updateUserDetails = new UpdateUserDetails();

		updateUserDetails.setFirstName(users.getFirstName());

		return updateUserDetails;
	}

	@Override
	public DeleteUserDetails deleteUserDetails(String emailId) {

		repo.deleteById(emailId);

		DeleteUserDetails deleteDetails = new DeleteUserDetails();

		deleteDetails.setEmailId(emailId);
		deleteDetails.setMessage("your user account is deleted");

		return deleteDetails;
	}

	@Override
	public ResetPassword updateNewPassword(ResetPassword request, String emailId) {

		Optional<Users> data = repo.findById(emailId);

		Users user = data.get();

		user.setPassword(request.getUpdatedPassword());
		user.setEmailId(emailId);

		Users userSaved = repo.save(user);

		ResetPassword resetPassword = new ResetPassword();

		resetPassword.setUpdatedPassword(userSaved.getPassword());
		resetPassword.setMessage("your password is updated sucessfully");

		return resetPassword;
	}

	@Override
	public List<EmailIdOrders> loadOrdersByEmailId(String emailId) {

		RestTemplate resttemplate = new RestTemplate();

		String url = "http://localhost:5505/order/" + emailId;

		// ResponseEntity<EmailIdOrders[]> data = resttemplate.getForEntity(url,
		// EmailIdOrders[].class);

		ResponseEntity<List<EmailIdOrders>> data = resttemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<EmailIdOrders>>() {
				});

		List<EmailIdOrders> orders = data.getBody();

		return orders;
	}

	@Override
	public UsersResponse getUser(String emailId) {
		Optional<Users> data = repo.findById(emailId);

		Users user = data.get();
		UsersResponse userResponseById = new UsersResponse();

		userResponseById.setEmailId(user.getEmailId());
		userResponseById.setFirstName(user.getFirstName());
		userResponseById.setLastName(user.getLastName());
		userResponseById.setPassword(user.getPassword());
		userResponseById.setMobileNumber(user.getMobileNummber());

		return userResponseById;
	}

	@Override
	public String insertOrderDetailsFromUser(OrderRequest request) {

		String url = "http://localhost:5505/order/management/insert/user/order/details";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<OrderRequest> requestEntity = new HttpEntity<OrderRequest>(request, headers);

		ResponseEntity<OrderRequest> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				OrderRequest.class);

		responseEntity.getBody();

		return "your order details are inserted";
	}

	@Override
	public List<EmailIdPayment> loadPaymentByEmailId(String emailId) {
		
		
		String url="http://localhost:9990/payment/"+emailId;
		
		RestTemplate restTemplate=new RestTemplate();
		
	ResponseEntity<List<EmailIdPayment>> data=	restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<EmailIdPayment>>() {
		});
		
	List<EmailIdPayment> paymentDetails= data.getBody();
		return paymentDetails;
	}

}
