package servlets;

import service.GroupService;
import service.GroupServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 */
@WebServlet("/analyticalInf")
public class AnalyticalServlet extends DispatcherServlet {
	
	private GroupService groupService = new GroupServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("users") != null){
			GroupMapper.numberUsers(request,response,groupService);
		}else if (request.getParameter("ncon") != null){
			GroupMapper.numberContacts(request,response,groupService);
		}else if (request.getParameter("grus") != null){
			GroupMapper.quantityGroupsUser(request,response,groupService);
		}else if (request.getParameter("avrg") != null){
			GroupMapper.averageNumberContactsGroups(request,response,groupService);
		}else if (request.getParameter("avru") != null){
			GroupMapper.averageNumberContactsUser(request,response,groupService);
		}else if (request.getParameter("min10") != null){
			GroupMapper.userWithContactsMin_10(request,response,groupService);
		}else {
		
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/analytical_inf/analitic_inf.jsp").forward(request,response);
	}
	
	
	static class GroupMapper{
		
		
		private static void numberUsers(HttpServletRequest request, HttpServletResponse response,
		                                GroupService groupService) throws  ServletException, IOException {
			try {
				request.setAttribute("number", groupService.numberUsers());
				request.getRequestDispatcher("/WEB-INF/views/analytical_inf/number_users.jsp").forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		private static void numberContacts(HttpServletRequest request, HttpServletResponse response,
		                                   GroupService groupService) throws  ServletException, IOException {
			try {
				String users_ncon = request.getParameter("users_ncon");
				request.setAttribute("number", groupService.numberContacts(users_ncon));
				request.getRequestDispatcher("/WEB-INF/views/analytical_inf/number_contacts.jsp").forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		private static void quantityGroupsUser(HttpServletRequest request, HttpServletResponse response,
		                                       GroupService groupService) throws  ServletException, IOException {
			try {
				String grus = request.getParameter("get_group");
				request.setAttribute("number", groupService.quantityGroupsUser(grus));
				request.getRequestDispatcher("/WEB-INF/views/analytical_inf/quantity_groups_user.jsp")
						.forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		private static void averageNumberContactsGroups(HttpServletRequest request, HttpServletResponse response,
		                                                GroupService groupService) throws ServletException, IOException {
			try {
				request.setAttribute("number", groupService.averageNumberContactsGroups());
				request.getRequestDispatcher("/WEB-INF/views/analytical_inf/average_number_contacts_groups.jsp").forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		private static void averageNumberContactsUser(HttpServletRequest request, HttpServletResponse response,
		                                                 GroupService groupService) throws ServletException, IOException  {
			try {
				request.setAttribute("number", groupService.averageNumberContactsUser());
				request.getRequestDispatcher("/WEB-INF/views/analytical_inf/average_number_contacts_user.jsp").forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		private static void userWithContactsMin_10(HttpServletRequest request, HttpServletResponse response,
		                                                GroupService groupService) throws ServletException, IOException {
			
			try {
				request.setAttribute("users", groupService.userWithContactsMin_10());
				request.getRequestDispatcher("/WEB-INF/views/analytical_inf/user_with_contacts_min_10.jsp")
						.forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
