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

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

@WebServlet(urlPatterns = "/cars/modifier")


public class VehicleModifServlet extends HttpServlet{

private static String Vue_Formulaire= "/WEB-INF/views/vehicles/modif.jsp";
	
	@Autowired ReservationService reservationService;
	@Autowired ClientService clientService;
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
			request.setAttribute("listVehicles", this.vehicleService.findAll());

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
		   String id_string = request.getParameter("vehicle");
		   String manufacturer = request.getParameter("manufacturer");
		   String seats_string = request.getParameter("seats");
		   
		   int id = Integer.parseInt(id_string);
		   int seats = Integer.parseInt(seats_string);
		   
		   try {
			
			Vehicle vehicle = new Vehicle(id,manufacturer, seats);
			vehicleService.modifier(vehicle);
			
		}catch(ServiceException e) {
			e.printStackTrace();
		}
		   response.sendRedirect("/rentmanager/cars");
	}
	
	
}
