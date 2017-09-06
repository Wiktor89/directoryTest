package net.directory.controller;

import net.directory.models.User;
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
@RequestMapping("/rest/admin/")
@RestController
public class GroupController {
	
	private ContactService contactService;
	private GroupService groupService;
	private static final Logger LOGGER = Logger.getLogger(GroupController.class);
	
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
	
	@RequestMapping(value = "/removeGroupRest", method = RequestMethod.GET)
	public void removeGroup(@RequestParam("id") Integer id) {
		try {
			this.groupService.removeGroup(id);
		} catch (SQLException e) {
			LOGGER.error("could not delete group");
		}
	}
	
	@RequestMapping(value = "/updateGroupRest", method = RequestMethod.GET)
	public void updateGroup(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		try {
			this.groupService.updateGroup(id,name);
		} catch (SQLException e) {
			LOGGER.error("could not update group");
		}
	}
	
	@RequestMapping(value = "/numberUsersRest", method = RequestMethod.GET)
	public Integer numberUsers() {
		Integer count = 0;
		try {
			count = this.groupService.numberUsers();
		} catch (SQLException e) {
			LOGGER.error("could not display the number of users");
		}
		return count;
	}
	
	@RequestMapping(value = "/numberContactsRest", method = RequestMethod.GET)
	public Integer numberContacts(@RequestParam ("name") String name) {
		Integer count = 0;
		try {
			count = groupService.numberContacts(name);
		} catch (SQLException e) {
			LOGGER.error("could not display the number of contacts from the user");
		}
		return count;
	}
	
	@RequestMapping(value = "/quantityGroupsUserRest", method = RequestMethod.GET)
	public Integer quantityGroupsUser(@RequestParam ("name") String name) {
		Integer count = 0;
		try {
			count = groupService.quantityGroupsUser(name);
		} catch (SQLException e) {
			LOGGER.error("could not display the number of user groups");
		}
		return count;
	}
	
	@RequestMapping(value = "/averageNumberContactsGroupsRest", method = RequestMethod.GET)
	public Integer averageNumberContactsGroups() {
		Integer count = 0;
		try {
			count = groupService.averageNumberContactsGroups();
		} catch (SQLException e) {
			LOGGER.error("unable to display the average number of contacts");
		}
		return count;
	}
	
	@RequestMapping(value = "/averageNumberContactsUserRest", method = RequestMethod.GET)
	public Integer averageNumberContactsUser() {
		Integer count = 0;
		try {
			count = groupService.averageNumberContactsUser();
		} catch (SQLException e) {
			LOGGER.error("unable to display the average number of contacts");
		}
		return count;
	}
	
	@RequestMapping(value = "/userWithContactsMin_10Rest", method = RequestMethod.GET)
	public Set<User> userWithContactsMin_10() {
		Set<User> users = new TreeSet<>();
		try {
			users = this.groupService.userWithContactsMin_10();
			System.out.println(users);
		} catch (SQLException e) {
			LOGGER.error("unable to display users with number of contacts < 10");
		}
		return users;
	}
	
	@RequestMapping(value = "/removeContactRest", method = RequestMethod.GET)
	public void removeContact(@RequestParam("id") Integer id) {
		try {
			this.contactService.removeContact(id);
		} catch (SQLException e) {
			LOGGER.error("could not delete contact");
		}
	}
	
	@RequestMapping(value = "/updateContactRest", method = RequestMethod.GET)
	public void updateContact(@RequestParam("id") Integer id, @RequestParam("newName") String newName,
	                                  @RequestParam("phone") String phone, @RequestParam("email") String email) {
		List<String> attContact = new ArrayList<>();
		attContact.add(0, String.valueOf(id));
		attContact.add(1, newName);
		attContact.add(2, phone);
		attContact.add(3, email);
		try {
			contactService.updateContact(attContact);
		} catch (SQLException | IOException e) {
			LOGGER.error("could not update contact");
		}
	}
}
