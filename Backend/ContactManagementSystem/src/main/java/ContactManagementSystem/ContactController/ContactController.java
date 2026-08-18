package ContactManagementSystem.ContactController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ContactModel addContact(@Valid @RequestBody ContactModel contactModel) {
		return contactServices.addContact(contactModel);
	}
	
	@PutMapping("/updateContact/{id}")
	public ContactModel updateContact(@PathVariable  String id,@Valid @RequestBody ContactModel contactModel) {
		return contactServices.updateContact(id, contactModel);
	}
	
	@DeleteMapping("/deleteContact/{id}")
	public ContactModel deleteContact(@PathVariable String id) {
		return contactServices.deleteContact(id);
	}
	
	@GetMapping("/getAllContacts")
	public List<ContactModel> getAllContact(){
		return contactServices.getAllContacts();
	}
	
	@GetMapping("/getContactById/{id}")
	public ContactModel getContactById(@PathVariable String id) {
		return contactServices.getContactById(id);
	}
	
	

}
