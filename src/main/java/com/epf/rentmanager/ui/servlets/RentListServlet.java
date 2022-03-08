package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;

@WebServlet(urlPatterns = "/rents")

public class RentListServlet extends HttpServlet{
	
	private static String Vue_Formulaire= "/WEB-INF/views/rents/list.jsp";
		
	@Autowired ReservationService reservationService;
	@Autowired ReservationDao reservationDao;
	
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
			
			String delete = request.getParameter("delete");
			

			if (delete != null) {
				int id_reservation = Integer.parseInt(delete);
				Reservation reservation = new Reservation(id_reservation);
								
				try {
					reservationService.delete(reservation);
					
				} catch (ServiceException e) {
					e.printStackTrace();
				}
			}
			
			response.sendRedirect("/rentmanager/rents");


	}

}
