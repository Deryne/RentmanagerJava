package com.epf.rentmanager.main;

import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			ApplicationContext context = new
			AnnotationConfigApplicationContext(AppConfiguration.class);
			ClientService clientService = context.getBean(ClientService.class);
			VehicleService vehicleService = context.getBean(VehicleService.class);

	}

}
