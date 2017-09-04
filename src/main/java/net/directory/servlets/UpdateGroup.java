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
@WebServlet("/updateGroup")
public class UpdateGroup extends HttpServlet {
	
	private ApplicationContext context;
	private static final Logger LOGGER = Logger.getLogger(UpdateGroup.class);
	private GroupService serviceGroup;
	private Integer id;
	
	public UpdateGroup() {
		this.serviceGroup = new GroupServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		if (title.trim().length() > 0){
			try {
				serviceGroup.updateGroup(id,title);
			} catch (SQLException e) {
				LOGGER.error("Could not update group");
				request.getRequestDispatcher("/WEB-INF/views/exception/group_exists.html")
						.forward(request,response);
			}
			response.sendRedirect("/updateGroup");
			
		}else {
			request.getRequestDispatcher("/WEB-INF/views/exception/empty_field.html")
					.forward(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			out.print(HtmlPage.getHtmlPageUpdate(serviceGroup.getGroups()));
		} catch (SQLException e) {
			LOGGER.error("No group");
			request.getRequestDispatcher("/addGroup")
					.forward(request,response);
		}
		id = Integer.valueOf(request.getParameter("id"));
		Set<Group> groups = null;
		try {
			groups = serviceGroup.getGroups();
		} catch (SQLException e) {
			LOGGER.error("No group");
			request.getRequestDispatcher("/addGroup")
					.forward(request,response);
		}
		for (Group group : groups){
			if (group.getId() == id){
				out.print(HtmlPage.getHtmlPageUpdate(group));
			}
		}
		out.close();
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		serviceGroup = (GroupService) context.getBean("groupService");
	}
}
