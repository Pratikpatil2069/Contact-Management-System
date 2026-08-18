package ContactManagementSystem.ContactRepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ContactManagementSystem.ContactModel.ContactModel;

@Repository
public interface ContactRepository extends MongoRepository<ContactModel,String>{
	public boolean existsByEmail(String email);
	public boolean existsByPhoneNumber(String phoneNumber);
}
