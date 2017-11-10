package net.directory.controller;

import net.directory.models.User;
import net.directory.service.GroupService;
import net.directory.utilits.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Set;

/**
 *
 */
@RequestMapping("/admin/")
@RestController
public class AdminController {
	
	private GroupService groupService;
	private static final Logger LOGGER = Logger.getLogger(AdminController.class);
	
	@Autowired(required = true)
	@Qualifier(value = "groupService")
	public void seGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	@RequestMapping(value = "/numberUsersRest", method = RequestMethod.GET)
	public String numberUsers() {
		String s = null;
		Integer count = 0;
		try {
			count = this.groupService.numberUsers();
			s = Util.numberUsers(count);
		} catch (SQLException e) {
			LOGGER.error("could not display the number of users");
		}
		return s;
	}
	
	@RequestMapping(value = "/numberContactsRest/{name}", method = RequestMethod.GET)
	public String numberContacts(@PathVariable("name") String name) {
		String s = null;
		Integer count = 0;
		try {
			count = groupService.numberContacts(name);
			s = Util.numberContact(count);
		} catch (SQLException e) {
			LOGGER.error("could not display the number of contacts from the user");
		}
		return s;
	}
	
	@RequestMapping(value = "/quantityGroupsUserRest/{name}", method = RequestMethod.GET,consumes="application/json")
	public String quantityGroupsUser(@PathVariable("name") String name) {
		String s = null;
		Integer count = 0;
		try {
			count = groupService.quantityGroupsUser(name);
			s = Util.quantityGroupsUserRest(count);
		} catch (SQLException e) {
			LOGGER.error("could not display the number of user groups");
		}
		return s;
	}
	
	@RequestMapping(value = "/averageNumberContactsGroupsRest", method = RequestMethod.GET)
	public String averageNumberContactsGroups() {
		String s = null;
		Integer count = 0;
		try {
			count = groupService.averageNumberContactsGroups();
			s = Util.averageNumberContactsGroupsRest(count);
		} catch (SQLException e) {
			LOGGER.error("unable to display the average number of contacts");
		}
		return s;
	}
	
	@RequestMapping(value = "/averageNumberContactsUserRest", method = RequestMethod.GET)
	public String averageNumberContactsUser() {
		String s = null;
		Integer count = 0;
		try {
			count = groupService.averageNumberContactsUser();
			s = Util.averageNumberContactsUser(count);
		} catch (SQLException e) {
			LOGGER.error("unable to display the average number of contacts");
		}
		return s;
	}
	
	@RequestMapping(value = "/userWithContactsMin_10Rest", method = RequestMethod.GET)
	public String userWithContactsMin_10() {
		String s = null;
		try {
			Set<User> users1 = this.groupService.userWithContactsMin_10();
			s = Util.userWithContactsMin_10(users1.size());
		} catch (SQLException e) {
			LOGGER.error("unable to display users with number of contacts < 10");
		}
		return s;
	}

}
