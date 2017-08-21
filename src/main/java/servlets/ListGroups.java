package servlets;

import models.Group;
import service.GroupServiceImpl;
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
@WebServlet("/listGroups")
public class ListGroups extends HttpServlet {
	
	private GroupServiceImpl service = null;
	
	public ListGroups() {
		this.service = new GroupServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Set<Group> groups = service.getGroups();
		PrintWriter out = response.getWriter();
		out.print(HtmlPage.getHtmlPage(groups));
		out.close();
		try {
			service.removeGroup(Integer.valueOf(request.getParameter("id")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/listGroups");
	}
	
}
