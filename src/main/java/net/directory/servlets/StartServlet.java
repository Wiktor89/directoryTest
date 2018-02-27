package net.directory.servlets;

import net.directory.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@WebServlet("/startServlet")
public class StartServlet extends HttpServlet {
	
	private ApplicationContext context;
	private static final Logger LOGGER = LoggerFactory.getLogger(StartServlet.class);
	private ContactService serviceContact;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		List<String> attrUser = new ArrayList<>();
//		attrUser.add(0,request.getParameter("login"));
//		attrUser.add(1,request.getParameter("password"));
//		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
////		aut.getAuthorities().;
////		User user = userService.authenticate(aut);
//		User user = new User();
//		if (user != null && user.getEnable()) {
//					response.sendRedirect("/selection");
//				} else request.getRequestDispatcher("/WEB-INF/views/exception/exsPageLogin.html")
//						.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/loginPage.jsp")
				.forward(request,response);
	}
	
	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(
				this.getServletContext());
		this.serviceContact = (ContactService) context.getBean("contactService");
	}
}
