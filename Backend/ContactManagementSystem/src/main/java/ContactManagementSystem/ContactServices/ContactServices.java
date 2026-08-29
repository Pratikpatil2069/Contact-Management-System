package ContactManagementSystem.ContactServices;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ContactManagementSystem.ContactModel.ContactModel;
import ContactManagementSystem.ContactRepository.ContactRepository;
import ContactManagementSystem.ContactsDTO.ContactRequest;
import ContactManagementSystem.ContactsDTO.ContactResponse;
import ContactManagementSystem.Exception.DuplicateResourceException;
import ContactManagementSystem.Exception.ResourceNotFoundException;

@Service
public class ContactServices {
	
	@Autowired
	private ContactRepository contactRepository;
	
	public ContactResponse addContact(ContactRequest contactRequest) {
		if(contactRepository.existsByEmail(contactRequest.getEmail())) {
			throw new DuplicateResourceException("Email is Already Exist: "+contactRequest.getEmail());
		}
		if(contactRepository.existsByPhoneNumber(contactRequest.getPhoneNumber())) {
			throw new DuplicateResourceException("Phone Number is Already Exist: "+contactRequest.getPhoneNumber());
		}
		ContactModel contactModel = new ContactModel();
		
		contactModel.setName(contactRequest.getName());
		contactModel.setEmail(contactRequest.getEmail());
		contactModel.setPhoneNumber(contactRequest.getPhoneNumber());
		contactModel.setAddress(contactRequest.getAddress());
		
		ContactModel response= contactRepository.save(contactModel);
		
		ContactResponse contactResponse=new ContactResponse();
		
		contactResponse.setId(response.getId());
		contactResponse.setName(response.getName());
		contactResponse.setEmail(response.getEmail());
		contactResponse.setPhoneNumber(response.getPhoneNumber());
		contactResponse.setAddress(response.getAddress());
		
		return contactResponse;
		
	}
	
	public ContactResponse updateContact(String id,ContactRequest contactRequest) {
		
		ContactModel contactModel=contactRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not Found with id: "+id));
		
		
			if(contactRepository.existsByEmail(contactRequest.getEmail())) {
				throw new DuplicateResourceException("Email is Already Exist: "+contactRequest.getEmail());
			}
			if(contactRepository.existsByPhoneNumber(contactRequest.getPhoneNumber())) {
				throw new DuplicateResourceException("Phone Number is Already Exist: "+contactRequest.getPhoneNumber());
			}
			
			
			contactModel.setName(contactRequest.getName());
			contactModel.setEmail(contactRequest.getEmail());
			contactModel.setPhoneNumber(contactRequest.getPhoneNumber());
			contactModel.setAddress(contactRequest.getAddress());
			
			ContactModel response= contactRepository.save(contactModel);
			
			ContactResponse contactResponse=new ContactResponse();
			
			contactResponse.setId(response.getId());
			contactResponse.setName(response.getName());
			contactResponse.setEmail(response.getEmail());
			contactResponse.setPhoneNumber(response.getPhoneNumber());
			contactResponse.setAddress(response.getAddress());
			
			return contactResponse;
		
	}
	
	public void deleteContact(String id) {
		
		if(contactRepository.existsById(id)) {
			
			contactRepository.deleteById(id);
			
		}else {
			
			 throw new ResourceNotFoundException("Contact Not Found with id: "+id);
			 
		}
		
		 
		 
	}
	
	public ContactResponse getContactById(String id) {
		
		ContactModel contactModel= contactRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not Found with id: "+id));
		
		ContactResponse contactResponse = new ContactResponse();
		
		contactResponse.setId(contactModel.getId());
		contactResponse.setName(contactModel.getName());
		contactResponse.setEmail(contactModel.getEmail());
		contactResponse.setPhoneNumber(contactModel.getPhoneNumber());
		contactResponse.setAddress(contactModel.getAddress());
		
		return contactResponse;
		
	}
	
	public List<ContactResponse> getAllContacts(){
		
		List<ContactModel>list= contactRepository.findAll();
		
		List<ContactResponse>response=new ArrayList<>();
		
		for(ContactModel contactModel:list) {
			
			ContactResponse contactResponse=new ContactResponse();
			
			contactResponse.setId(contactModel.getId());
			contactResponse.setName(contactModel.getName());
			contactResponse.setEmail(contactModel.getEmail());
			contactResponse.setPhoneNumber(contactModel.getPhoneNumber());
			contactResponse.setAddress(contactModel.getAddress());
			
			response.add(contactResponse);
		}
		return response;
		
	}

}
