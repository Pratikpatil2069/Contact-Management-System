package ContactManagementSystem.ContactModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
@Document
public class ContactModel {
	@Id
	private String id;
	
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
