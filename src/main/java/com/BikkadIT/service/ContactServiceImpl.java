package com.BikkadIT.service;

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

}
