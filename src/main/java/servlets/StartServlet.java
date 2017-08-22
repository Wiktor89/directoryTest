package servlets;

import models.User;
import service.ContactService;
import service.ContactServiceImpl;

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
	
	private ContactService contactService = new ContactServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> attrUser = new ArrayList<>();
		attrUser.add(0,request.getParameter("login"));
		attrUser.add(1,request.getParameter("password"));
			try {
				User user = contactService.authorizationPage(attrUser);
				if (user != null && user.getEnable()){
					response.sendRedirect("/selection");
				}else request.getRequestDispatcher("/WEB-INF/views/exception/exsPageLogin.html")
						.forward(request,response);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/start-page/loginPage.html")
				.forward(request,response);
	}
}
