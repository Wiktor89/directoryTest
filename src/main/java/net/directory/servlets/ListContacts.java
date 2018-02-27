package net.directory.servlets;

import net.directory.models.Contact;
import net.directory.service.ContactService;
import net.directory.utilits.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Set;

/**
 *
 */
@WebServlet("/listContacts")
public class ListContacts extends HttpServlet {
	
	private ApplicationContext context;
	private ContactService serviceContact;
	private static final Logger LOGGER = LoggerFactory.getLogger(ListContacts.class);
	
//	public ListContacts() {
//		this.serviceContact = new ContactServiceImpl();
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Set<Contact> contacts = serviceContact.getContacts();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			request.setCharacterEncoding ("UTF-8");
			out.print(HtmlPage.getHtmlPageRem(contacts));
			out.close();
			serviceContact.removeContact(Integer.valueOf(request.getParameter("id")));
		} catch (SQLException e) {
			LOGGER.error("Could not load contact list");
			e.printStackTrace();
		}
		response.sendRedirect("/listContacts");
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		serviceContact = (ContactService) context.getBean("contactService");
	}
}
