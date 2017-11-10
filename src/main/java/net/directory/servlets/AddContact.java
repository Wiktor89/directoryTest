package net.directory.servlets;

import net.directory.factories.EntityFactory;
import net.directory.models.Entity;
import net.directory.service.ContactService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
	
	private ApplicationContext context;
	private static final Logger LOGGER = Logger.getLogger(AddContact.class);
	private ContactService serviceContact = null;
	
	protected void doPost(HttpServletRequest request,
	                      HttpServletResponse response) throws ServletException, IOException {
		List<String> attContact = new ArrayList<>();
		attContact.add(0,request.getParameter("fio"));
		attContact.add(1,request.getParameter("phone"));
		attContact.add(2,request.getParameter("email"));
		try {
			Entity entity = EntityFactory.create("con",attContact);
			this.serviceContact.addContact(entity);
		} catch (IOException | SQLException e) {
			LOGGER.error("Could not add contact");
		}
		response.sendRedirect("/addContact");
	}
	
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/add/addContact.html")
				.forward(request,response);
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
						this.getServletContext());
		serviceContact = (ContactService) context.getBean("contactService");
	}
}
