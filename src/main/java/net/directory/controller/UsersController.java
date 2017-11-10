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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
@RequestMapping("/users/")
@RestController
public class UsersController {
	
	private GroupService groupService;
	private ContactService contactService;
	private static final Logger LOGGER = Logger.getLogger(UsersController.class);
	
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
	
	@RequestMapping(value = "/addContactRest", method = RequestMethod.POST)
	public void addContact(@RequestBody Contact contact) {
		List<String> attContact = new ArrayList<>();
		attContact.add(0,contact.getFio());
		attContact.add(1,contact.getPhone());
		attContact.add(2,contact.getEmail());
		try {
			Entity entity = EntityFactory.create("con",attContact);
			this.contactService.addContact(entity);
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
	
	@RequestMapping(value = "/appGroupContactRest/{name}/{nameG}", method = RequestMethod.PUT)
	public void removeGroupContact(@PathVariable("name") String name, @PathVariable("nameG") String nameG){
		List<String> attr = new ArrayList<>();
		attr.add(0,name);
		attr.add(1, nameG);
		try {
			this.contactService.appGroupContact(attr);
		} catch (SQLException e) {
			LOGGER.error("could not delete a group from a contact");
		}
	}
	
	@RequestMapping(value = "/addGroupRest", method = RequestMethod.POST, consumes="application/json")
	public void addGroup(@RequestBody Group group){
		if (group.getName().trim().length() > 0){
			List<String> attr = new ArrayList<>();
			attr.add(0,group.getName());
			try {
				Entity entity = EntityFactory.create("gro",attr);
				groupService.addGroup(entity);
			} catch (SQLException | IOException e) {
				LOGGER.error("Could not add group");
			}
		}else {

		}
	}
	
	@RequestMapping(value = "/getContactsGroupRest/{name}", method = RequestMethod.GET)
	public Set<Contact> getContactsGroupsRest(@PathVariable("name") String name) {
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

	@RequestMapping(value = "/updateContactRest/{id}", method = RequestMethod.PUT)
	public void updateContact(@RequestBody Contact contact,
							  @PathVariable(value = "id") String id) {
		List<String> attContact = new ArrayList<>();
		attContact.add(0, id);
		attContact.add(1, contact.getFio());
		attContact.add(2, contact.getPhone());
		attContact.add(3, contact.getEmail());
		try {
			contactService.updateContact(attContact);
		} catch (SQLException | IOException e) {
			LOGGER.error("could not update contact");
		}
	}

	@RequestMapping(value = "/removeContactRest/{id}", method = RequestMethod.DELETE)
	public void removeContact(@PathVariable(value = "id") Integer id) {
		try {
			this.contactService.removeContact(id);
		} catch (SQLException e) {
			LOGGER.error("could not delete contact");
		}
	}

	@RequestMapping(value = "/updateGroupRest/{id}", method = RequestMethod.PUT)
	public void updateGroup(@PathVariable(value = "id") Integer id,
							@RequestBody Group group) {
		try {
			this.groupService.updateGroup(id,group.getName());
		} catch (SQLException e) {
			LOGGER.error("could not update group");
		}
	}

	@RequestMapping(value = "/removeGroupRest/{id}", method = RequestMethod.DELETE)
	public void removeGroup(@PathVariable("id") String id){
		try {
			this.groupService.removeGroup(Integer.valueOf(id));
		} catch (SQLException e) {
			LOGGER.error("could not delete group");
		}
	}
}

