package net.directory.servlets;

import net.directory.factories.EntityFactory;
import net.directory.models.Entity;
import net.directory.service.GroupService;
import net.directory.service.GroupServiceImpl;
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
@WebServlet("/addGroup")
public class AddGroup extends HttpServlet {
	
	private ApplicationContext context;
	private static final Logger LOGGER = Logger.getLogger(AddGroup.class);
	private GroupService serviceGroup;
	
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
			LOGGER.error("Could not add group");
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
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		serviceGroup = (GroupService) context.getBean("groupService");
	}
}
