package net.directory.servlets;

import net.directory.models.Role;
import net.directory.models.User;
import net.directory.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	
	private ApplicationContext context;
	private UserService userService;
	private static final Logger LOGGER = Logger.getLogger(AddContact.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username,password,new Role("ROLE_USER"));
		LOGGER.info("add user "+user);
		LOGGER.debug("add user "+user);
		userService.addUser(user);
		request.getRequestDispatcher("/loginPage.jsp").forward(request,response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/add/addUser.jsp")
				.forward(request,response);
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		userService = (UserService) context.getBean("userService");
	}
}
