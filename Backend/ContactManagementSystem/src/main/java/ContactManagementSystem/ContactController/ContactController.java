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

import ContactManagementSystem.ContactModel.ContactModel;
import ContactManagementSystem.ContactServices.ContactServices;
import ContactManagementSystem.Response.ApiResponse;
import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/Contact")
public class ContactController {
	
	@Autowired
	private ContactServices contactServices;
	
	@PostMapping("/addContact")
	public ResponseEntity<ApiResponse<ContactModel>> addContact(@Valid @RequestBody ContactModel contactModel) {
		
		ContactModel contact= contactServices.addContact(contactModel);
		
		ApiResponse<ContactModel> response=new ApiResponse<>(true,"Contact Creted Successfully", contact, LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/updateContact/{id}")
	public ResponseEntity<ApiResponse<ContactModel>> updateContact(@PathVariable  String id,@Valid @RequestBody ContactModel contactModel) {
		
		ContactModel contact= contactServices.updateContact(id, contactModel);
		
		ApiResponse<ContactModel> response=new ApiResponse<>(true,"Contact Updated Successfully", contact, LocalDateTime.now());

		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/deleteContact/{id}")
	public ResponseEntity<ApiResponse<Void>>  deleteContact(@PathVariable String id) {
		
		 contactServices.deleteContact(id);
		 
		 ApiResponse<Void> response=new ApiResponse<>(true,"Contact Deleted Successfully", null, LocalDateTime.now());

		 return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getAllContacts")
	public ResponseEntity<ApiResponse<List<ContactModel>>>  getAllContact(){
		
		List<ContactModel> list= contactServices.getAllContacts();
		
		ApiResponse<List<ContactModel>> response=new ApiResponse<>(true,"Fetched All Contacts  Successfully", list, LocalDateTime.now());

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getContactById/{id}")
	public ResponseEntity<ApiResponse<ContactModel>>  getContactById(@PathVariable String id) {
		
		ContactModel contact= contactServices.getContactById(id);
		
		ApiResponse<ContactModel> response=new ApiResponse<>(true,"Fetched Contact Successfully", contact, LocalDateTime.now());

		return ResponseEntity.ok(response);
	}
	
	

}
