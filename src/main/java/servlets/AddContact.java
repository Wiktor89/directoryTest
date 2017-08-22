package servlets;

import factories.EntityFactory;
import models.Entity;
import models.User;
import service.ContactServiceImpl;
import service.GroupServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@WebServlet("/addContact")
public class AddContact extends HttpServlet {
	
	private User user;
	private ContactServiceImpl serviceContact = null;
	private GroupServiceImpl serviceGroup = null;
	
	public AddContact() {
		this.serviceContact = new ContactServiceImpl();
		this.serviceGroup = new GroupServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request,
	                      HttpServletResponse response) throws ServletException, IOException {
		List<String> attContact = new ArrayList<>();
		attContact.add(0,request.getParameter("fio"));
		attContact.add(1,request.getParameter("phone"));
		attContact.add(2,request.getParameter("email"));
		try {
			Entity entity = EntityFactory.create("con",attContact);
			this.serviceContact.addContact(entity);
			System.out.println();
		} catch (IOException | SQLException e) {
		}
		response.sendRedirect("/addContact");
	}
	
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/add/addContact.html")
				.forward(request,response);
	}
	
}
