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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet(urlPatterns = "/cars")

public class  VehicleListServlet extends HttpServlet {
	
	private static String Vue_Formulaire= "/WEB-INF/views/vehicles/list.jsp";
	
	@Autowired VehicleService vehicleService;
	    
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
			request.setAttribute("vehicles", this.vehicleService.findAll());
		}catch(ServiceException e) {
			e.printStackTrace();
		}
		 this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request,response); 
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String delete = request.getParameter("delete");
		
		if (delete != null) {
			int id_vehicle = Integer.parseInt(delete);
			Vehicle vehicle = new Vehicle(id_vehicle);
			try {
				vehicleService.delete(vehicle);
				
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("/rentmanager/cars");

	}
}




	
	



