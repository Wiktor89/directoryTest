package net.directory.servlets;

import net.directory.service.UserService;
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
@WebServlet("/listUsers")
public class ListUsers extends HttpServlet {
	
	private ApplicationContext context;
	private UserService userService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("login").equalsIgnoreCase("login")){
		}
		response.sendRedirect("/listUsers");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("users", userService.getUser());
		request.getRequestDispatcher("/WEB-INF/views/show_lists/listUsers.jsp")
				.forward(request,response);
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		this.userService = (UserService) context.getBean("userService");
	}
}
