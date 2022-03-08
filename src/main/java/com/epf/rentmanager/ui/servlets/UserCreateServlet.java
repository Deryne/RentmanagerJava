package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.model.Client;

@WebServlet(urlPatterns = "/users/create")


public class UserCreateServlet extends HttpServlet{

private static String Vue_Formulaire= "/WEB-INF/views/users/create.jsp";
	
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
		
		 this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request,response); 

	}
	
    /** 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		   
		   DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   
		   String last_name = request.getParameter("last_name");
		   String first_name = request.getParameter("first_name");
		   String email = request.getParameter("email");
		   String birth_string = request.getParameter("birth");

		   LocalDate birth = LocalDate.parse(birth_string, df);
		   
		   try {
			Client client = new Client(last_name, first_name,email,birth);
			
			try {
				clientService.create(client);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			
		}catch(ServiceException e) {
			e.printStackTrace();
		}
		   response.sendRedirect("/rentmanager/users");

	}
	
	
}
