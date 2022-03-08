package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	

	/** 
	 * Appel de  la fonction create du DAO
	 * @param Vehicle
	 * Correpondant à la creation d'un profil vehicle
	 * @throws ServiceException
	 */
	public int create(Vehicle vehicle) throws ServiceException {
		if (!vehicle.Nb_placesValide()) {
			throw new ServiceException("Nombre de places <2 ou >9");
		} else {
			try {
				return vehicleDao.create(vehicle);
			} catch (DaoException e) {
				throw new ServiceException("Erreur dans la creation du vehicle");
			}
		}

	}

	/** 
	 * Appel de  la fonction modifier du DAO
	 * @param Vehicle
	 * Correpondant à l'update d'un profil vehicle
	 * @throws ServiceException
	 */
	public long modifier(Vehicle vehicle) throws ServiceException{
		if (!vehicle.Nb_placesValide()) {
			throw new ServiceException("Nombre de places <2 ou >9");
		} else {
			try {
				return vehicleDao.modifier(vehicle);
			} catch (DaoException e) {
				throw new ServiceException("Erreur dans la modification du vehicle");
			}
		}

	}
	
	/** 
	 * Appel de  la fonction delete du DAO
	 * @param Vehicle
	 * Correpondant à la suppression d'un profil vehicle
	 * @throws ServiceException
	 */
	public long delete(Vehicle vehicle) throws ServiceException {
		try {
			return this.vehicleDao.delete(vehicle);
		}catch(DaoException e) {
			throw new ServiceException("Erreur dans la suppression du vehicle");
		}

	}
	
	/** 
	 * Appel de  la fonction findById du DAO
	 * @param id
	 * Correpondant a l'id du vehicle cherche
	 * @return Vehicle
	 * Correspondant a un vehicle trouve a partir de son id
	 * @throws ServiceException
	 */
	public Vehicle findById(int id) throws ServiceException {
		try {
			return this.vehicleDao.findById(id).get();
		} catch (DaoException e) {
			throw new ServiceException("Probleme lors de l'execution de find id de vehicle");
		}
		
	}

	/** 
	 * Appel de  la fonction findAll du DAO
	 * @return List<Vehicle>
	 * Correspondant a la liste des vehicles enregistres
	 * @throws ServiceException
	 */
	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException("Probleme lors de l'execution de find all de vehicle");
		}

	}
	
	/** 
	 * Appel de  la fonction count du DAO
	 * @return int
	 * Correspondant au nombre de clients enregistres
	 */
	public int count() throws ServiceException {
		try {
			return this.vehicleDao.count();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Erreur lors de l'execution de la fonction count() de vehicle");
		}
	}
	
}
