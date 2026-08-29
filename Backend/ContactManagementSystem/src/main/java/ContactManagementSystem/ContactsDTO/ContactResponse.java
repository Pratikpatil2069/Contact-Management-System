package ContactManagementSystem.ContactsDTO;

import lombok.Data;

@Data
public class ContactResponse {
	
	private String id;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;

}
