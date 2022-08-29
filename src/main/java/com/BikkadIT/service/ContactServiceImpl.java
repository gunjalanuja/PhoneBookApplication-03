package com.BikkadIT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.model.Contact;
import com.BikkadIT.repository.ContactRepository;

@Service
public class ContactServiceImpl implements  ContactServiceI
{
    @Autowired
	private ContactRepository contactRepository;
    
	@Override
	public boolean saveContact(Contact contact) 
	{
		Contact save = contactRepository.save(contact);
		if(save!=null)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	@Override
	public List<Contact> getAllContact() 
	{
		List<Contact> findAll = contactRepository.findAll();
		      
		return findAll;
	}

	@Override
	public Contact getContactById(Integer contactId)
	{
	   Contact findById = contactRepository.findById(contactId).get();
		return findById;
	}

	@Override
	public boolean updateContact(Contact contact) 
	{
		Contact save = contactRepository.save(contact);
		if(save!=null)
		{
			return true;
		}
		else
		{
			
			return false;
		}
		
		
	}

	@Override
	public boolean deleteByIdHard(Integer contactId) 
	{
		boolean existsById = contactRepository.existsById(contactId);
		if(existsById)
		{
			contactRepository.deleteById(contactId);
			return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public boolean deleteByIdSoft(Integer contactId) 
	{
		Optional<Contact> findById = contactRepository.findById(contactId);
		if(findById.isPresent())
		{
			Contact contact = findById.get();
			contact.setActiveSwitch('N');
			contactRepository.save(contact);
			return true;
		}
		else
		{
		return false;
	}
	}

}
