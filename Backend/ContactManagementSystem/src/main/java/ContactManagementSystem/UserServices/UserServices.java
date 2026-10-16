package ContactManagementSystem.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ContactManagementSystem.Exception.DuplicateResourceException;
import ContactManagementSystem.Exception.InvalidCredentialsException;
import ContactManagementSystem.UserDTO.LoginRequest;
import ContactManagementSystem.UserDTO.LoginResponse;
import ContactManagementSystem.UserDTO.UserRequest;
import ContactManagementSystem.UserDTO.UserResponse;
import ContactManagementSystem.UserModel.UserModel;
import ContactManagementSystem.UserRepository.UserRepository;


@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private JwtServices jwtServices;
	
	public UserResponse registerUser(UserRequest userRequest) {
		
		if(userRepository.existsByUsername(userRequest.getUsername())) {
			throw new DuplicateResourceException("Username Already Exists");
		}
		
		if(userRepository.existsByEmail(userRequest.getEmail())) {
			throw new DuplicateResourceException("Email Already Exists");
		}
		
		UserModel userModel=new UserModel();
		
		userModel.setUsername(userRequest.getUsername());
		userModel.setEmail(userRequest.getEmail());
		userModel.setPassword(passwordEncoder.encode(userRequest.getUsername()));
		userModel.setRole("USER");
		
		UserModel response=userRepository.save(userModel);
		
		return toUserResponse(response);
	}
	
		public UserResponse registerAdmin(UserRequest userRequest) {
				
			if(userRepository.existsByUsername(userRequest.getUsername())) {
				throw new DuplicateResourceException("Username Already Exists");
			}
			
			if(userRepository.existsByEmail(userRequest.getEmail())) {
				throw new DuplicateResourceException("Email Already Exists");
			}
			
			UserModel userModel=new UserModel();
			
			userModel.setUsername(userRequest.getUsername());
			userModel.setEmail(userRequest.getEmail());
			userModel.setPassword(passwordEncoder.encode(userRequest.getUsername()));
			userModel.setRole("ADMIN");
			
			UserModel response=userRepository.save(userModel);
			
			return toUserResponse(response);
		}
		public LoginResponse loginUser(LoginRequest loginRequest) {
			
			UserModel userModel=userRepository.findByUsername(loginRequest.getUsername());
			
			if(userModel==null) {
				throw new InvalidCredentialsException("Invalid username or password");
			}
			
			if(passwordEncoder.matches(loginRequest.getPassword(),userModel.getPassword())) {
			
				 throw new InvalidCredentialsException("Invalid username or password");
			}
			
			String token= jwtServices.generateToken(userModel.getUsername(),userModel.getRole());
					
			LoginResponse loginResponse = new LoginResponse();
			
			loginResponse.setRole(userModel.getRole());
			loginResponse.setUsername(userModel.getUsername());
			loginResponse.setToken(token);
			
			return loginResponse;
			
		}
		 private UserResponse toUserResponse(UserModel user) {

		        UserResponse response = new UserResponse();

		        response.setId(user.getId());
		        response.setUsername(user.getUsername());
		        response.setEmail(user.getEmail());
		        response.setRole(user.getRole());

		        return response;
		   }
		
		
}
