package ContactManagementSystem.ContactsDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequest {
	
	
	@NotBlank(message="Please Enter Your Name")
	private String name;
	
	@NotBlank(message="Please Enter Your Email")
	@Email(message="Please Enter Valid Email")
	private String email;
	
	@NotBlank(message="Please Enter Your Name")
	private String phoneNumber;
	
	@NotBlank(message="Please Enter Your Name")
	private String address;

}
