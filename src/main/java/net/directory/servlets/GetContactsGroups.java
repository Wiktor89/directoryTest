package net.directory.servlets;

import net.directory.models.Contact;
import net.directory.service.GroupService;
import net.directory.service.GroupServiceImpl;
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
@WebServlet("/getContactsGroup")
public class GetContactsGroups extends HttpServlet {
	
	private ApplicationContext context;
	private GroupService serviceGroup;
	
	public GetContactsGroups() {
		this.serviceGroup = new GroupServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Set<Contact> contacts = this.serviceGroup.getContactsGroup(request.getParameter("gro"));
			if (!contacts.isEmpty()){
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				request.setCharacterEncoding ("UTF-8");
				out.print(getHtmlPage(contacts));
				out.close();
			}else {
				response.sendRedirect("/getContactsGroup");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/show_lists/get_contacts_group.html")
				.forward(request,response);
	}
	
	private StringBuilder getHtmlPage(Set<Contact> contacts){
		StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n");
		stringBuilder.append("<html lang=\"en\">\n");
		stringBuilder.append("<head>\n<meta charset=\"UTF-8\">\n<title>Get Contacts Group</title>\n<style>" +
				"table{\n width: 100%;\nbackground: white;\ncolor: white;\nborder-spacing: 1px;\n}"+
				"td, th{\nbackground: maroon;\npadding: 5px;\n}\n</style>"+
				"</head>\n");
		stringBuilder.append("<body>\n");
		stringBuilder.append("<table>");
		stringBuilder.append("<tr>\n<th>id</th>\n<th>FIO</th>\n");
		stringBuilder.append("<th>Phone</th>\n<th>Email</th>");
		for (Contact contact : contacts){
			stringBuilder.append("<tr>\n<th>");
			stringBuilder.append(contact.getId());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getFio());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getPhone());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getEmail());
		}
		stringBuilder.append("</table>");
		stringBuilder.append("<body>\n");
		stringBuilder.append("</html>");

		System.out.println(stringBuilder);
		return stringBuilder;
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		serviceGroup = (GroupService) context.getBean("groupService");
	}
}
