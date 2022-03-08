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

@WebServlet(urlPatterns = "/rents/modifier")


public class RentModifServlet extends HttpServlet{

private static String Vue_Formulaire= "/WEB-INF/views/rents/modif.jsp";
	
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
			request.setAttribute("listUsers", this.clientService.findAll());
			request.setAttribute("listVehicles", this.vehicleService.findAll());
			request.setAttribute("listRents", this.reservationService.findAll());

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
		
		   DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		   String id_string = request.getParameter("rent");
		   String car_string = request.getParameter("vehicle");
		   String client_string = request.getParameter("client");
		   String debut_string = request.getParameter("begin");
		   String fin_string = request.getParameter("end");
		   
		   int id = Integer.parseInt(id_string);
		   int car = Integer.parseInt(car_string);
		   int client = Integer.parseInt(client_string);
		   LocalDate debut = LocalDate.parse(debut_string,df);
		   LocalDate fin = LocalDate.parse(fin_string,df);

		   try {
			   
			Reservation reservation = new Reservation(id, client, car, debut, fin);
			reservationService.modifier(reservation);
			
		}catch(ServiceException e) {
			e.printStackTrace();
		}
		   response.sendRedirect("/rentmanager/rents");

	}
	
	
}
