package net.directory.controller;


import net.directory.factories.EntityFactory;
import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.Group;
import net.directory.service.ContactService;
import net.directory.service.GroupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
@RequestMapping("/rest/users/")
@RestController
public class ContactController {
	
	private GroupService groupService;
	private ContactService contactService;
	private static final Logger LOGGER = Logger.getLogger(ContactController.class);
	
	@Autowired(required = true)
	@Qualifier(value = "contactService")
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "groupService")
	public void seGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	@RequestMapping(value = "/addContactRest", method = RequestMethod.GET)
	public void addContact(@RequestParam("name") String name) {
		List<String> attContact = new ArrayList<>();
		attContact.add(0,name);
		try {
			Entity entity = EntityFactory.create("con",attContact);
			this.contactService.addContact(entity);
			getContacts();
		} catch (SQLException | IOException e) {
			LOGGER.error("could not add contact");
		}
	}
	
	@RequestMapping(value = "/getContactsRest", method = RequestMethod.GET)
	public Set<Contact> getContacts() {
		Set<Contact> contacts = null;
		try {
			contacts = contactService.getContacts();
		} catch (IOException | SQLException e) {
			LOGGER.error("could not display contact list");
		}
		return contacts;
	}
	
	
	@RequestMapping(value = "/appGroupContactRest", method = RequestMethod.GET)
	public void removeGroupContact(@RequestParam("id") Integer id
			,@RequestParam("name") String name) {
		List<String> attr = new ArrayList<>();
		attr.add(1,name);
		attr.add(0, String.valueOf(id));
		try {
			this.contactService.appGroupContact(attr);
		} catch (SQLException e) {
			LOGGER.error("could not delete a group from a contact");
		}
	}
	
	@RequestMapping(value = "/addGroupRest", method = RequestMethod.GET)
	public String addGroup(@RequestParam("name") String name){
		if (name.trim().length() > 0){
			List<String> attr = new ArrayList<>();
			attr.add(0,name);
			try {
				Entity entity = EntityFactory.create("gro",attr);
				groupService.addGroup(entity);
			} catch (SQLException | IOException e) {
				LOGGER.error("Could not add group");
			}
		}else {
			return "No name group";
		}
		return name;
	}
	
	@RequestMapping(value = "/getContactsGroupRest", method = RequestMethod.GET)
	public Set<Contact> getContactsGroupsRest(@RequestParam("name") String name) {
		Set<Contact> contacts = new TreeSet<>();
		if (name.trim().length() > 0){
			try {
				contacts = this.groupService.getContactsGroup(name);
			} catch (SQLException e) {
				LOGGER.error("could not display group contacts");
			}
		}
		return contacts;
	}
	
	@RequestMapping(value = "/getGroupsRest", method = RequestMethod.GET)
	public Set<Group> getGroups() {
		Set<Group> groups = new TreeSet<>();
		try {
			groups = groupService.getGroups();
		} catch (SQLException e) {
			LOGGER.error("could not display groups");
		}
		return groups;
	}
}

