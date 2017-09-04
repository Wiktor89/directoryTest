package net.directory.controller;


import net.directory.models.Contact;
import net.directory.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

/**
 *
 */
@RestController
public class ContactController {
	
	private ContactService service;
	
	@Autowired(required = true)
	@Qualifier(value = "contactService")
	public void setContactService(ContactService contactService) {
		this.service = contactService;
	}
	
	@RequestMapping(value = "/removeContactRest", method = RequestMethod.GET)
	public void removeContact(@PathVariable("id") int id) {
		try {
			this.service.removeContact(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addContactRest", method = RequestMethod.GET)
	public String addContact() {
		Contact contact = new Contact("spring");
		try {
			this.service.addContact(contact);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/show_lists/list.html";
	}
	
	@RequestMapping(value = "/updateContactRest", method = RequestMethod.POST)
	public String updateContact() {
		
		Contact contact = new Contact("spring test");
		try {
			service.addContact(contact);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "add/addContact";
	}
	
	@RequestMapping(value = "/getContactsRest", method = RequestMethod.GET)
	public Set<Contact> getContacts() {
		Set<Contact> contacts = null;
		try {
			contacts = service.getContacts();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}
}

