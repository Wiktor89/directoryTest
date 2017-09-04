package net.directory.controller;

import net.directory.models.Group;
import net.directory.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 *
 */
@RestController
public class GroupController {
	
	
	private GroupService service;
	
	@Autowired(required = true)
	@Qualifier(value = "groupService")
	public void setGroupService(GroupService groupService) {
		this.service = groupService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addGroup(@RequestParam(value = "name") String name) {
		try {
			service.addGroup(new Group(name));
			System.out.println(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/addContactRest";
	}
}
