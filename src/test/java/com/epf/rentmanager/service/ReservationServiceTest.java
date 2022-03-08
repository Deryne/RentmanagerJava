package com.epf.rentmanager.service;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {
	
	 @InjectMocks
	   private ReservationService reservationService;

	   @Mock
	   private ReservationDao reservationDao;
	   
	   /**
	    * @throws DaoException
	    */
	   
	   @Test
	   public void findAll_should_fail_when_dao_throws_exception() throws DaoException {
	       // When
	       when(this.reservationDao.findAll()).thenThrow(DaoException.class);

	       // Then
	       assertThrows(ServiceException.class, () -> reservationService.findAll());
	   }
	   
	   /**
	    * @throws DaoException
	    */
	   @Test
	   public void count_dao_throws_exception() throws DaoException {
	       // When
	       when(reservationDao.count()).thenThrow(DaoException.class);
	 
	       // Then
	       assertThrows(ServiceException.class, () -> reservationService.count());
	   }
	   
	   /**
	    * @throws DaoException
	    */
	   @Test
	   public void delete_dao_throws_exception() throws DaoException {
		   
	        Reservation legalReservation = new Reservation(1 , 1, 1, LocalDate.parse("2020-03-01"),
	                LocalDate.parse("2020-03-04"));

	       // When
	       doThrow(new DaoException("Exception occured")).when(reservationDao).delete(legalReservation);
	       // Then
	       assertThrows(ServiceException.class, () -> reservationService.delete(legalReservation));
	   }
	   
	   /**
	    * @throws DaoException
	    */
	   @Test
	   public void create_dao_throws_exception() throws DaoException {
		   
	        Reservation legalReservation = new Reservation(1 , 1, 1, LocalDate.parse("2020-03-01"),
	                LocalDate.parse("2020-03-04"));	       
	       // When
	       when(reservationDao.create(legalReservation)).thenThrow(DaoException.class);
	       
	       // Then
	       assertThrows(ServiceException.class, () -> reservationService.create(legalReservation));
	   }
	   
	   /**
	    * @throws DaoException
	    */
	   @Test
	   public void modifier_dao_throws_exception() throws DaoException {
		   
	        Reservation legalReservation = new Reservation(1 , 1, 1, LocalDate.parse("2020-03-01"),
	                LocalDate.parse("2020-03-04"));	 	       
	       // When
	       when(reservationDao.modifier(legalReservation)).thenThrow(DaoException.class);
	       
	       // Then
	       assertThrows(ServiceException.class, () -> reservationService.modifier(legalReservation));
	   }

	
	   
	   

}
