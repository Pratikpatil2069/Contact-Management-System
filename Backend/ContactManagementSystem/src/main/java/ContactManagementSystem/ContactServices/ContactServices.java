package ContactManagementSystem.ContactServices;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ContactManagementSystem.ContactModel.ContactModel;
import ContactManagementSystem.ContactRepository.ContactRepository;
import ContactManagementSystem.Exception.ResourceException;

@Service
public class ContactServices {
	
	@Autowired
	private ContactRepository contactRepository;
	
	public ContactModel addContact(ContactModel contactModel) {
		if(contactRepository.existsByEmail(contactModel.getEmail())) {
			throw new ResourceException("Email is Already Exist: "+contactModel.getEmail());
		}
		if(contactRepository.existsByPhoneNumber(contactModel.getPhoneNumber())) {
			throw new ResourceException("Phone Number is Already Exist: "+contactModel.getPhoneNumber());
		}
		return contactRepository.save(contactModel);
	}
	
	public ContactModel updateContact(String id,ContactModel contactModel) {
		
		ContactModel oldContact=contactRepository.findById(id).orElseThrow(()->new ResourceException("Contact not Found with id: "+id));
		
		
			if(contactRepository.existsByEmail(contactModel.getEmail())) {
				throw new ResourceException("Email is Already Exist: "+contactModel.getEmail());
			}
			if(contactRepository.existsByPhoneNumber(contactModel.getPhoneNumber())) {
				throw new ResourceException("Phone Number is Already Exist: "+contactModel.getPhoneNumber());
			}
			oldContact.setName(contactModel.getName());
			oldContact.setEmail(contactModel.getEmail());
			oldContact.setPhoneNumber(contactModel.getPhoneNumber());
			oldContact.setAddress(contactModel.getAddress());
			return contactRepository.save(oldContact);
		
		
	}
	
	public ContactModel deleteContact(String id) {
		ContactModel contact=contactRepository.findById(id).orElseThrow(()->new ResourceException("Contact not Found with id: "+id));
		 contactRepository.deleteById(id);
		 return contact;
	}
	
	public ContactModel getContactById(String id) {
		return contactRepository.findById(id).orElseThrow(()->new ResourceException("Contact not Found with id: "+id));
	}
	
	public List<ContactModel> getAllContacts(){
		return contactRepository.findAll();
	}

}
