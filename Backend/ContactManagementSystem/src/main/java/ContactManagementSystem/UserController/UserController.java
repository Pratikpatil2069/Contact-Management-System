package ContactManagementSystem.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ContactManagementSystem.UserDTO.LoginRequest;
import ContactManagementSystem.UserDTO.LoginResponse;
import ContactManagementSystem.UserDTO.UserRequest;
import ContactManagementSystem.UserDTO.UserResponse;
import ContactManagementSystem.UserServices.UserServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/register-user")
	public UserResponse registerUser(@Valid @RequestBody UserRequest userRequest) {
		return userServices.registerUser(userRequest);
	}
	
	@PostMapping("/register-admin")
	public UserResponse registerAdmin(@Valid @RequestBody UserRequest userRequest) {
		return userServices.registerAdmin(userRequest);
	}
	
	@PostMapping("/login")
	public LoginResponse userLogin(@Valid @RequestBody LoginRequest loginRequest) {
		return userServices.loginUser(loginRequest);
	}
}
