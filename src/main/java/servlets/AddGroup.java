package servlets;

import factories.EntityFactory;
import models.Entity;
import service.GroupServiceImpl;

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
@WebServlet("/addGroup")
public class AddGroup extends HttpServlet {
	
	private GroupServiceImpl serviceGroup = null;
	
	public AddGroup() {
		this.serviceGroup = new GroupServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> attGroup = new ArrayList<>();
		attGroup.add(0,request.getParameter("title"));
		try {
			Entity entity = EntityFactory.create("gro",attGroup);
			this.serviceGroup.addGroup(entity);
		} catch (SQLException e) {
			request.getRequestDispatcher("/WEB-INF/views/exception/group_exists.html")
					.forward(request,response);
		}
		response.sendRedirect("/addGroup");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/add/addGroup.html")
				.forward(request,response);
	}
}
