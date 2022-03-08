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
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet(urlPatterns = "/home")

public class HomeServlet extends HttpServlet {
	
	private static String Vue_Formulaire= "/WEB-INF/views/home.jsp";
	
	@Autowired VehicleService vehicleService;
	@Autowired ClientService clientService;
	@Autowired ReservationService reservationService;

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
			request.setAttribute("nb_clients", this.clientService.count());
			request.setAttribute("nb_vehicles", this.vehicleService.count());
			request.setAttribute("nb_reservations", this.reservationService.count());

		}catch(ServiceException e) {
			e.printStackTrace();
		} 
		getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request,response); 
	}		

	
}

