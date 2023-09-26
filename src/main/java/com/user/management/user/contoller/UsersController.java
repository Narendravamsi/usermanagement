package com.user.management.user.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.user.model.DeleteUserDetails;
import com.user.management.user.model.EmailIdOrders;
import com.user.management.user.model.EmailIdPayment;
import com.user.management.user.model.OrderRequest;
import com.user.management.user.model.ResetPassword;
import com.user.management.user.model.UpdateUserDetails;
import com.user.management.user.model.UsersRequest;
import com.user.management.user.model.UsersResponse;
import com.user.management.users.service.UsersService;

@RestController
public class UsersController {
	@Autowired
	UsersService service;

	@Value("${project.name.message}")
	private String welcomeMsg;

	@RequestMapping(method = RequestMethod.GET, path = "get/all/users")
	public List<UsersResponse> loadAllUsersDetails() {
		List<UsersResponse> details = service.loadAllUsersDetails();

		return details;
	}

	@RequestMapping(method = RequestMethod.GET, path = "get/by/id/{emaiId}")
	public UsersResponse loadByUserId(@PathVariable String emaiId) {

		UsersResponse userdDetails = service.loadByUserId(emaiId);

		return userdDetails;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/insert/users/details")
	public String insertUserDetails(@RequestBody UsersRequest request) {
		String user = service.insertUserDetails(request);

		return user;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "update/users/details/{emailId}")
	public UpdateUserDetails updateUserDetails(@RequestBody UpdateUserDetails request, @PathVariable String emailId) {

		UpdateUserDetails details = service.updateUserDetails(request, emailId);

		return details;
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "delete/users/details/{emailId}")
	public DeleteUserDetails deleteUserDetails(@PathVariable String emailId) {

		DeleteUserDetails deleteDetails = service.deleteUserDetails(emailId);

		return deleteDetails;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "reset/password/{emailId}")
	public ResetPassword updateNewPassword(@RequestBody ResetPassword request, @PathVariable String emailId) {

		ResetPassword password = service.updateNewPassword(request, emailId);

		return password;
	}

	@RequestMapping(method = RequestMethod.GET, path = "get/user/order/{emailId}")
	public List<EmailIdOrders> loadOrdersByEmailId(@PathVariable("emailId") String emailId) {

		return service.loadOrdersByEmailId(emailId);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/message")
	public String printMessage() {

		return "helloworld";

	}

//Query parameters
	@RequestMapping(method = RequestMethod.GET, path = "/user")
	public UsersResponse getUser(@RequestParam(name = "emailId") String emailId) {

		return service.getUser(emailId);

	}

	@RequestMapping(method = RequestMethod.POST, path = "/insert/user/order")
	public String insertOrderDetailsFromUser(@RequestBody OrderRequest request) {

		return service.insertOrderDetailsFromUser(request);
	}

	@RequestMapping(method = RequestMethod.GET, path = "get/user/payment/details/{emailId}")
	public List<EmailIdPayment> loadPaymentByEmailId(@PathVariable String emailId) {

		return service.loadPaymentByEmailId(emailId);

	}

	@GetMapping("/welcome")
	public String displayWelcomeMsg() {
		return "projectName:" + welcomeMsg;
	}

}