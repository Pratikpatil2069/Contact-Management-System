package ContactManagementSystem.ContactController;

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
import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/Contact")
public class ContactController {
	
	@Autowired
	private ContactServices contactServices;
	
	@PostMapping("/addContact")
	public ResponseEntity<ContactModel> addContact(@Valid @RequestBody ContactModel contactModel) {
		ContactModel contact= contactServices.addContact(contactModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(contact);
	}
	
	@PutMapping("/updateContact/{id}")
	public ResponseEntity<ContactModel> updateContact(@PathVariable  String id,@Valid @RequestBody ContactModel contactModel) {
		ContactModel contact= contactServices.updateContact(id, contactModel);
		return ResponseEntity.ok(contact);
	}
	
	@DeleteMapping("/deleteContact/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable String id) {
		 contactServices.deleteContact(id);
		 return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/getAllContacts")
	public ResponseEntity<List<ContactModel>> getAllContact(){
		List<ContactModel> list= contactServices.getAllContacts();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/getContactById/{id}")
	public ResponseEntity<ContactModel> getContactById(@PathVariable String id) {
		ContactModel contact= contactServices.getContactById(id);
		return ResponseEntity.ok(contact);
	}
	
	

}
