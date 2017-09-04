package net.directory.servlets;

import net.directory.models.User;
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
@WebServlet("/startServlet")
public class StartServlet extends HttpServlet {
	
	private ApplicationContext context;
	private static final Logger LOGGER = Logger.getLogger(StartServlet.class);
	private ContactService serviceContact;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> attrUser = new ArrayList<>();
		attrUser.add(0,request.getParameter("login"));
		attrUser.add(1,request.getParameter("password"));
			try {
				User user = serviceContact.authorizationPage(attrUser);
				if (user != null && user.getEnable()){
					response.sendRedirect("/selection");
				}else request.getRequestDispatcher("/WEB-INF/views/exception/exsPageLogin.html")
						.forward(request,response);
				
				
			} catch (SQLException e) {
				LOGGER.error("Authorization of this user did not pass");
				e.printStackTrace();
			}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/start-page/loginPage.html")
				.forward(request,response);
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		serviceContact = (ContactService) context.getBean("contactService");
	}
}
