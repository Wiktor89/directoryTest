package servlets;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("onclick"));
		System.out.println(request.getParameter("login"));
		if (request.getParameter("login").equalsIgnoreCase("login")){
			System.out.println("true");
		}
		response.sendRedirect("/listUsers");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/show_lists/list.html")
				.forward(request,response);
	}
}
