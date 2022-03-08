package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {
	
	public ClientDao() {}
		
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client( nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS = "SELECT COUNT(id) AS count FROM Client;";
	private static final String UPDATE_CLIENT = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";
	private static final String FIND_EMAIL = "Select email FROM Client where email=?;";
	
	

	
	/**
	 * Fonction qui permet de creer un client
	 * @param Client qui va etre cree
	 * @return int
	 * @throws DaoException
	 */
	public int create(Client client) throws DaoException {
		int id = 0;
		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY,
					id = Statement.RETURN_GENERATED_KEYS);
		){
			pstmt.setString(1, client.getLastname());
			pstmt.setString(2, client.getName());
			pstmt.setString(3, client.getEmail());
			
			LocalDate birthdate = client.getBirthdate();
			pstmt.setDate(4, Date.valueOf(birthdate));
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation du client");
		}		

	}
	
	/**
	 * Fonction qui permet de modifier un client
	 * @param Client qui va etre modifie
	 * @return int
	 * @throws DaoException
	 */
	public long modifier(Client client) throws DaoException{
		try (
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement  pstmt = conn.prepareStatement(UPDATE_CLIENT);
			)
		
		{
			pstmt.setString(1, client.getLastname());
			pstmt.setString(2, client.getName());
			pstmt.setString(3, client.getEmail());
			
			LocalDate birthdate = client.getBirthdate();
			pstmt.setDate(4, Date.valueOf(birthdate));
			
			pstmt.setInt(5, client.getId());

			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Probleme lors de la modification du client");
		}
		
	}
	
	/**
	 * Fonction qui permet de supprimer un client
	 * @param Client qui va etre supprime
	 * @return int
	 * @throws DaoException
	 */
	public long delete(Client client) throws DaoException {
		
		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY);

		    )
		{
			pstmt.setInt(1, client.getId());
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la suppression du client");
		}

	}
	
	/**
	 * @param client
	 * @return boolean
	 * @throws DaoException
	 */
	public boolean findEmail(Client client) throws DaoException{
		boolean EmailExistant = true;

		try (
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement  pstmt = conn.prepareStatement(FIND_EMAIL);
			)
		{
			pstmt.setString(1, client.getEmail());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmailExistant = false;
			}
			return EmailExistant;
			
		} catch (SQLException e) {
			throw new DaoException("Mail existant");
		}	
		

	}

	/**
	 * Fonction qui permet de trouver un client par son id
	 * @param int
	 * @return Optional<Client>
	 * @throws DaoException
	 * @throws ServiceException 
	 */
	public Optional<Client> findById(int id) throws DaoException {
		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);
			)
		{
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			String clientLastName = rs.getString("nom");
			String clientFirstName = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();
			
			Client client = new Client(
				id, clientLastName, clientFirstName, clientEmail, clientBirthdate);
						
			return Optional.of(client);			
			
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de l'execution de find id de client");
		}
		
	}

	/**
	 * Fonction qui permet d'afficher tous les clients crees
	 * @return List<Client>
	 * @throws DaoException
	 */
	public List<Client> findAll() throws DaoException {
		
        List<Client> list_clients = new ArrayList<Client>();

		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);
			)
		{
			ResultSet rs = pstmt.executeQuery();			

			while (rs.next()) {

				int clientid = rs.getInt("id");
				String clientLastName = rs.getString("nom");
				String clientFirstName = rs.getString("prenom");
				String clientEmail = rs.getString("email");
				LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();
                
                Client client = new Client();
                
                client.setId(clientid);
                client.setLastname(clientLastName);
                client.setName(clientFirstName);
                client.setEmail(clientEmail);
                client.setBirthdate(clientBirthdate);
                
                list_clients.add(client);
            }

		} catch (SQLException e) {
			throw new DaoException("Probleme lors de l'execution de find all de client");
		}
		return list_clients;

	}
	
	
	/**
	 * Fonction qui permet de retourner le nombre de clients crees
	 * @return int
	 * @throws DaoException
	 */
	public int count() throws DaoException {
		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(COUNT_CLIENTS);
			)
		{
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("count");
			
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de l'execution de la fonction count() de client");
		}
	}

}
