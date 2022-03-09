package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;

@Service
public class ClientService {

	private ClientDao clientDao;
	
	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	

	/** 
	 * Appel de  la fonction create du DAO
	 * @param Client
	 * Correpondant à la creation d'un profil client
	 * @throws ServiceException
	 * @throws DaoException 
	 */
	public long create(Client client) throws ServiceException, DaoException {
		if (!client.isLegal(client)) {
			throw new ServiceException("Un age 18 ans ou plus est requis");
		} else if (!client.LenghtName()) {
			throw new ServiceException("Prenom possedant plus de 3 caracteres requis");
		} else if (!client.LenghtLastname()) {
			throw new ServiceException("Nom possedant plus de 3 caracteres requis");
		}
		else if(!findEmail(client)){
			throw new ServiceException("Mail existant");
		}
		else {
			try {
				return clientDao.create(client);
			} catch (DaoException e) {
				throw new ServiceException("Erreur dans la creation du client");
			}
		}
	}
	
	public boolean findEmail(Client client) throws ServiceException{
		try {
			return clientDao.findEmail(client);
		} catch (DaoException e) {
			throw new ServiceException("Mail existant");
		}
	}

	/** 
	 * Appel de  la fonction modifier du DAO
	 * @param Client
	 * Correpondant à l'update d'un profil client
	 * @throws ServiceException
	 */
	public long modifier(Client client) throws ServiceException {
		if (!client.isLegal(client)) {
			throw new ServiceException("Un age 18 ans ou plus est requis");
		} else if (!client.LenghtName()) {
			throw new ServiceException("Prenom possedant plus de 3 caracteres requis");
		} else if (!client.LenghtLastname()) {
			throw new ServiceException("Nom possedant plus de 3 caracteres requis");
		}else {
			try {
				return clientDao.modifier(client);
			} catch (DaoException e) {
				throw new ServiceException("Erreur dans la modification du client");
			}
		}
	}
	
	/** 
	 * Appel de  la fonction delete du DAO
	 * @param Client
	 * Correpondant à la suppression d'un profil client
	 * @throws ServiceException
	 */
	public long delete(Client client) throws ServiceException {
		try {
			return this.clientDao.delete(client);
		}catch(DaoException e) {
			throw new ServiceException("Erreur dans la suppression du client");
		}
	}
	
	/** 
	 * Appel de  la fonction findById du DAO
	 * @param id
	 * Correpondant a l'id du client cherche
	 * @return Client
	 * Correspondant a un client trouve a partir de son id
	 * @throws ServiceException
	 */
	public Client findById(int id) throws ServiceException {
		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			throw new ServiceException("Probleme lors de l'execution de find id de client");
		}		
	}

	/** 
	 * Appel de  la fonction findAll du DAO
	 * @return List<Client>
	 * Correspondant a la liste des clients enregistres
	 * @throws ServiceException
	 */
	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException("Probleme lors de l'execution de find all de client");
		}
		
	}
	
	/** 
	 * Appel de  la fonction count du DAO
	 * @return int
	 * Correspondant au nombre de clients enregistres
	 * @throws DaoException 
	 */
	public int count() throws ServiceException{
		try {
			return this.clientDao.count();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Erreur lors de l'execution de la fonction count() de client");
		}
	}
	
}
