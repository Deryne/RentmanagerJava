package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;

@WebServlet(urlPatterns = "/users")

public class UserListDeleteServlet extends HttpServlet {
	
	private static String Vue_Formulaire= "/WEB-INF/views/users/list.jsp";
	
	@Autowired 
	ClientService clientService;
	
    /** 
     * @throws ServletException
     */
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    /** 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			request.setAttribute("listUsers", this.clientService.findAll());
		}catch(ServiceException e) {
			e.printStackTrace();
		}
		 this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request,response); 
	}
	
    /** 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String delete = request.getParameter("delete");

		if (delete != null) {
			int id_client = Integer.parseInt(delete);
			Client client = new Client(id_client);
			try {
				clientService.delete(client);
				
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("/rentmanager/users");

}

}

