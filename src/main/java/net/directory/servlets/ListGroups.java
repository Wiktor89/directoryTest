package net.directory.servlets;

import net.directory.models.Group;
import net.directory.service.GroupService;
import net.directory.service.GroupServiceImpl;
import net.directory.utilits.HtmlPage;
import org.apache.log4j.Logger;
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
@WebServlet("/listGroups")
public class ListGroups extends HttpServlet {
	
	private ApplicationContext context;
	private static final Logger LOGGER = Logger.getLogger(ListGroups.class);
	private GroupService serviceGroup;
	
	public ListGroups() {
		this.serviceGroup = new GroupServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Set<Group> groups = null;
		try {
			groups = serviceGroup.getGroups();
		} catch (SQLException e) {
			LOGGER.error("No groups");
			request.getRequestDispatcher("/addGroup")
					.forward(request,response);
		}
		LOGGER.info("Groups "+ groups);
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding ("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(HtmlPage.getHtmlPage(groups));
		out.close();
		try {
			serviceGroup.removeGroup(Integer.valueOf(request.getParameter("id")));
		} catch (SQLException e) {
			LOGGER.error("Do not display group list");
			request.getRequestDispatcher("/addGroup")
					.forward(request,response);
		}
		response.sendRedirect("/listGroups");
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		serviceGroup = (GroupService)  context.getBean("groupService");
	}
}
