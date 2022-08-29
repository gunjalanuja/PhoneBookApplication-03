package com.BikkadIT.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.model.Contact;
import com.BikkadIT.repository.ContactRepository;
import com.BikkadIT.service.ContactServiceI;

@RestController
public class ContactController 
{
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
 private	ContactServiceI contactServiceI;
	
	@PostMapping(value="/saveContact",consumes="application/json")
	public ResponseEntity<String> saveContact(@RequestBody Contact contact)
	{
		boolean saveContact = contactServiceI.saveContact(contact);
		if(saveContact==true)
		{
			String msg="Contact saved sucessfully";
			return new ResponseEntity<String>(msg,HttpStatus.OK);
					
		}
		else
		{
			String msg="Contact  not saved ";
			return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
			
		}
	}
@GetMapping(value="/getAllContact",produces="application/json")	
public ResponseEntity<List<Contact>> getAllContact()
{
	
	
	List<Contact> allContact = contactServiceI.getAllContact();
	if( allContact!=null)
	{
		return new ResponseEntity<List<Contact>>(allContact,HttpStatus.OK);
	}
	else
	{
		String msg="Data not found";
		return new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
	}
	
}
@GetMapping(value="/getContactById/{contactId}",produces="application/json")
public ResponseEntity<Contact> getContactById(@PathVariable Integer contactId)
{
	Contact contactById = contactServiceI.getContactById(contactId);
	return new ResponseEntity<Contact>(contactById,HttpStatus.OK);
}
@PutMapping(value="/updateContact",produces="application/json")
public ResponseEntity<String> updateContact(@RequestBody Contact contact)
{
	boolean updateContact = contactServiceI.updateContact(contact);
	if(updateContact!=false)
	{
		String msg="Contact updated Sucessfully";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	else
	{
	String msg="Contact not updated Sucessfully";
	return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
	}
}
@DeleteMapping(value="/deleteById/{contactId}")
public ResponseEntity<String> deleteById(@PathVariable Integer contactId)
{
	boolean deleteByIdHard = contactServiceI.deleteByIdHard(contactId);
	if(deleteByIdHard)
	{
	return new ResponseEntity<String>("Record deleted Sucessfully",HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("Record not deleted Sucessfully",HttpStatus.OK);
	}
}
@GetMapping(value="/getAllContactsoft",produces="application/json")	//**
public ResponseEntity<List<Contact>> getAllContactSoft()
{
    List<Contact> contacts = contactRepository.findAll();
    Stream<Contact> stream = contacts.stream();
    Stream<Contact> filter = stream.filter(contact->contact.getActiveSwitch()=='Y');
    List<Contact> collect = filter.collect(Collectors.toList());
    return (ResponseEntity<List<Contact>>) collect;
    
	
}
}
