package com.epf.rentmanager.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.dao.ReservationDao;

@Service
public class ReservationService {

	private ReservationDao reservationDao;
	
	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	

	
	/** 
	 * Appel de  la fonction create du DAO
	 * @param Reservation
	 * Correpondant à la creation d'une reservation
	 * @throws ServiceException
	 */
	public long create(Reservation reservation) throws ServiceException {
		if (reservation.ResaTropLongue()) {
			throw new ServiceException("Reservation trop longue (superieure à 7 jours)");
		} else {
			try {
				return reservationDao.create(reservation);
			} catch (DaoException e) {
				throw new ServiceException("Erreur dans la creation de la reservation");
			}
		}

	}
	

	/** 
	 * Appel de  la fonction modifier du DAO
	 * @param Reservation
	 * Correpondant à l'update d'une reservation
	 * @throws ServiceException
	 */
	public long modifier(Reservation reservation) throws ServiceException{
		if (reservation.ResaTropLongue()) {
			throw new ServiceException("Reservation trop longue (superieure à 7 jours)");
		} else {
			try {
				return reservationDao.modifier(reservation);
			} catch (DaoException e) {
				throw new ServiceException("Erreur dans la modification de la reservation");
			}
		}
	}
	
	/** 
	 * Appel de  la fonction delete du DAO
	 * @param Reservation
	 * Correpondant à la suppression d'une reservation
	 * @throws ServiceException
	 */
	public long delete(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.delete(reservation);
		}catch(DaoException e) {
			throw new ServiceException("Erreur dans la suppression de la reservation");
		}
	}
	
	/** 
	 * Appel de  la fonction findAll du DAO
	 * @return List<Reservation>
	 * Correspondant a la liste des reservations creees
	 * @throws ServiceException
	 */
	public List<Reservation> findAll() throws ServiceException {
		try {
			return this.reservationDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException("Probleme lors de l'execution de find all de la reservation");
		}

	}
	
	/** 
	 * Appel de  la fonction count du DAO
	 * @return int
	 * Correspondant au nombre de reservations enregistrees
	 * @throws ServiceException
	 */
	public int count() throws ServiceException {
		try {
			return this.reservationDao.count();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Erreur lors de l'execution de la fonction count() de reservation");
		}
	}
	
}
