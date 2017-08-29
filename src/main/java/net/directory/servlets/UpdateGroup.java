package net.directory.servlets;

import net.directory.models.Group;
import net.directory.service.GroupServiceImpl;
import net.directory.utilits.HtmlPage;
import org.apache.log4j.Logger;

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
	
	private static final Logger LOGGER = Logger.getLogger(UpdateGroup.class);
	private GroupServiceImpl service = null;
	private Integer id;
	
	public UpdateGroup() {
		this.service = new GroupServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		if (title.trim().length() > 0){
			try {
				service.updateGroup(id,title);
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
		out.print(HtmlPage.getHtmlPageUpdate(service.getGroups()));
		id = Integer.valueOf(request.getParameter("id"));
		Set<Group> groups = service.getGroups();
		for (Group group : groups){
			if (group.getId() == id){
				out.print(HtmlPage.getHtmlPageUpdate(group));
			}
		}
		out.close();
	}
}
