package servlets;

import models.Contact;
import service.ContactServiceImpl;
import utilits.HtmlPage;

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
	
	private ContactServiceImpl service = null;
	
	public ListContacts() {
		this.service = new ContactServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Set<Contact> contacts = service.getContacts();
			PrintWriter out = response.getWriter();
			out.print(HtmlPage.getHtmlPageRem(contacts));
			out.close();
			service.removeContact(Integer.valueOf(request.getParameter("id")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/listContacts");
	
	}

	
}
