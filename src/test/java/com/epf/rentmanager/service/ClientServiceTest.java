package com.epf.rentmanager.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

   @InjectMocks
   private ClientService clientService;

   @Mock
   private ClientDao clientDao;

   /**
    * @throws DaoException
    */
   @Test
   public void findAll_should_fail_when_dao_throws_exception() throws DaoException {
       // When
       when(this.clientDao.findAll()).thenThrow(DaoException.class);

       // Then
       assertThrows(ServiceException.class, () -> clientService.findAll());
   }

   /**
    * @throws DaoException
    */
   @Test
   public void count_dao_throws_exception() throws DaoException {
       // When
       when(clientDao.count()).thenThrow(DaoException.class);
 
       // Then
       assertThrows(ServiceException.class, () -> clientService.count());
   }
   
   /**
    * @throws DaoException
    */
   @Test
   public void delete_dao_throws_exception() throws DaoException {
	   
       Client legalClient = new Client("Smith", "John", "smith.john@epf.fr", LocalDate.parse("2000-11-02"));

       // When
       doThrow(new DaoException("Exception occured")).when(clientDao).delete(legalClient);
       // Then
       assertThrows(ServiceException.class, () -> clientService.delete(legalClient));
   }
   
   /**
    * @throws DaoException
    */
   @Test
   public void create_dao_throws_exception() throws DaoException {
	   
       Client legalClient = new Client("Smith", "John", "smith.john@epf.fr", LocalDate.parse("2000-11-02"));
       
       // When
       when(clientDao.create(legalClient)).thenThrow(DaoException.class);
       
       // Then
       assertThrows(ServiceException.class, () -> clientService.create(legalClient));
   }
   
   /**
    * @throws DaoException
    */
   @Test
   public void modifier_dao_throws_exception() throws DaoException {
	   
       Client legalClient = new Client("Smith", "John", "smith.john@epf.fr", LocalDate.parse("2000-11-02"));
       
       // When
       when(clientDao.modifier(legalClient)).thenThrow(DaoException.class);
       
       // Then
       assertThrows(ServiceException.class, () -> clientService.modifier(legalClient));
   }
   
}
