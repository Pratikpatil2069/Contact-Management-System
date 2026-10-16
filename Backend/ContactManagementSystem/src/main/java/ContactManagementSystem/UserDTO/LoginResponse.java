package ContactManagementSystem.UserDTO;

import lombok.Data;

@Data
public class LoginResponse {
	
	private String username;
	
	private String role;
	
	private String token;
}
