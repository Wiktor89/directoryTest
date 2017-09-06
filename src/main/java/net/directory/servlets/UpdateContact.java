package net.directory.servlets;

import net.directory.models.Contact;
import net.directory.service.ContactService;
import net.directory.service.ContactServiceImpl;
import net.directory.utilits.HtmlPage;
import org.apache.log4j.Logger;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
@WebServlet("/updateContact")
public class UpdateContact extends HttpServlet {
	
	private ApplicationContext context;
	private static final Logger LOGGER = Logger.getLogger(UpdateContact.class);
	private ContactService serviceContact;
	private Integer id;
	
	public UpdateContact() {
		this.serviceContact = new ContactServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> attrContact = new ArrayList<>();
		attrContact.add(0, String.valueOf(id));
		String fio = request.getParameter("fio");
		attrContact.add(1, fio);
		if (fio.trim().length() > 0) {
			if (request.getParameter("phone").trim().length() > 0) {
				attrContact.add(2, request.getParameter("phone"));
			} else {
				attrContact.add(2, "nop");
			}
			if (request.getParameter("email").trim().length() > 0) {
				attrContact.add(3, request.getParameter("email"));
			} else {
				attrContact.add(3, "noe");
			}
			try {
				serviceContact.updateContact(attrContact);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("/updateContact");
		}else {
			LOGGER.error("Could not update contact");
			request.getRequestDispatcher("/WEB-INF/views/exception/empty_field.html")
					.forward(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding ("UTF-8");
		try {
			out.print(HtmlPage.getHtmlPageUpdateContacts(serviceContact.getContacts()));
			id = Integer.valueOf(request.getParameter("id"));
			Set<Contact> contacts = serviceContact.getContacts();
			for (Contact contact : contacts){
				if (contact.getId() == id){
					out.print(HtmlPage.getHtmlPageUpdateContact(contact));
				}
			}
		} catch (SQLException e) {
			LOGGER.error("No contact id");
			e.printStackTrace();
		}
		out.close();
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		serviceContact = (ContactService) context.getBean("contactService");
	}
}
