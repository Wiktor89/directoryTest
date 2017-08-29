package net.directory.servlets;

import net.directory.service.ContactService;
import net.directory.service.ContactServiceImpl;
import org.apache.log4j.Logger;

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
@WebServlet("/appContactGroup")
public class AppContactGroup extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(AppContactGroup.class);
	private ContactService service = new ContactServiceImpl();
	
	public AppContactGroup() {
		this.service = new ContactServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> attr = new ArrayList<>();
		attr.add(0,request.getParameter("fio").trim());
		attr.add(1,request.getParameter("group").trim());
		if(request.getParameter("appGroup") != null){
			try {
				service.appGroupContact(attr);
			} catch (SQLException e) {
				LOGGER.error("");
				request.getRequestDispatcher("/WEB-INF/views/exception/no_group_contact.html")
						.forward(request,response);
			}
		}else if (request.getParameter("remGroup") != null){
			try {
				service.removeGroupContact(attr);
			} catch (SQLException e) {
				request.getRequestDispatcher("/WEB-INF/views/exception/no_group_contact.html")
						.forward(request,response);
			}
		}
		response.sendRedirect("/appContactGroup");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/add/app_group_contact.html")
				.forward(request,response);
	}
}
