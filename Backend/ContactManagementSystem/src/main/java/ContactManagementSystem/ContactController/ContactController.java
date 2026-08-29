package ContactManagementSystem.ContactController;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ContactManagementSystem.ContactServices.ContactServices;
import ContactManagementSystem.ContactsDTO.ContactRequest;
import ContactManagementSystem.ContactsDTO.ContactResponse;
import ContactManagementSystem.Response.ApiResponse;
import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/Contact")
public class ContactController {
	
	@Autowired
	private ContactServices contactServices;
	
	@PostMapping("/addContact")
	public ResponseEntity<ApiResponse<ContactResponse>> addContact(@Valid @RequestBody ContactRequest contactRequest) {
		
		ContactResponse contactResponse= contactServices.addContact(contactRequest);
		
		ApiResponse<ContactResponse> response=new ApiResponse<>(true,"Contact Added Successfully", contactResponse, LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/updateContact/{id}")
	public ResponseEntity<ApiResponse<ContactResponse>> updateContact(@PathVariable  String id,@Valid @RequestBody ContactRequest contactRequest) {
		
		ContactResponse contactResponse= contactServices.updateContact(id, contactRequest);
		
		ApiResponse<ContactResponse> response=new ApiResponse<>(true,"Contact Updated Successfully", contactResponse, LocalDateTime.now());

		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/deleteContact/{id}")
	public ResponseEntity<ApiResponse<Void>>  deleteContact(@PathVariable String id) {
		
		 contactServices.deleteContact(id);
		 
		 ApiResponse<Void> response=new ApiResponse<>(true,"Contact Deleted Successfully", null, LocalDateTime.now());

		 return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getAllContacts")
	public ResponseEntity<ApiResponse<List<ContactResponse>>>  getAllContact(){
		
		List<ContactResponse> contactResponse= contactServices.getAllContacts();
		
		ApiResponse<List<ContactResponse>> response=new ApiResponse<>(true,"Fetched All Contacts  Successfully", contactResponse, LocalDateTime.now());

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getContactById/{id}")
	public ResponseEntity<ApiResponse<ContactResponse>>  getContactById(@PathVariable String id) {
		
		ContactResponse contactResponse= contactServices.getContactById(id);
		
		ApiResponse<ContactResponse> response=new ApiResponse<>(true,"Fetched Contact Successfully", contactResponse, LocalDateTime.now());

		return ResponseEntity.ok(response);
	}
	
	

}
