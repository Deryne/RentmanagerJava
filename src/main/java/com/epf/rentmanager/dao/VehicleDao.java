package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {
	
	public VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES = "SELECT COUNT(id) AS count FROM Vehicle;";
	private static final String UPDATE_VEHICLE = "UPDATE Vehicle SET constructeur=?, nb_places=? WHERE id=?;";

	/**
	 * Fonction qui permet de creer un vehicle
	 * @param Vehicle qui va etre cree
	 * @return int
	 * @throws DaoException
	 */
	public int create(Vehicle vehicle) throws DaoException {
		int id = 0;
		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY,
					id = Statement.RETURN_GENERATED_KEYS);
		){
			pstmt.setString(1, vehicle.getConstructeur());
			pstmt.setInt(2, vehicle.getNb_places());

			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation du vehicle");
		}

	}

	/**
	 * Fonction qui permet de modifier un client
	 * @param Client qui va etre modifie
	 * @return int
	 * @throws DaoException
	 */
	public int modifier(Vehicle vehicle) throws DaoException{
		try (
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement  pstmt = conn.prepareStatement(UPDATE_VEHICLE);
			)
		{
				pstmt.setString(1, vehicle.getConstructeur());
				pstmt.setInt(2, vehicle.getNb_places());
				
				pstmt.setInt(3, vehicle.getId());

				return pstmt.executeUpdate();
				
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la modification du client");
		}

	}
	
	
	/**
	 * Fonction qui permet de supprimer un vehicle
	 * @param Vehicle qui va etre supprime
	 * @return int
	 * @throws DaoException
	 */
	public int delete(Vehicle vehicle) throws DaoException {
		try(
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);
			)
		{
			pstmt.setInt(1, vehicle.getId());
			return pstmt.executeUpdate();	

		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la suppression du vehicle");
		}

	}
 
	/**
	 * Fonction qui permet de trouver un vehicle par son id
	 * @param int
	 * @return Optional<Vehicle>
	 * @throws DaoException
	 */
	public Optional<Vehicle> findById(int id) throws DaoException {
		try(	
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(FIND_VEHICLE_QUERY);
			)
		{
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			String vehicleConstructeur = rs.getString("constructeur");
			int vehicleNb_places = rs.getInt("nb_places");
			
			Vehicle vehicle = new Vehicle(id, vehicleConstructeur, vehicleNb_places);
			
			return Optional.of(vehicle);
			
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de l'execution de find id de voiture");
		}

	}

	/**
	 * Fonction qui permet d'afficher tous les vehicles crees
	 * @return List<Vehicle>
	 * @throws DaoException
	 */
	public List<Vehicle> findAll() throws DaoException {
		 List<Vehicle> list_vehicle = new ArrayList<Vehicle>();

			try(
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement  pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);
				)
			{
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {

					int vehicleId = rs.getInt("id");
					String vehicleConstructeur = rs.getString("constructeur");
					int vehicleNb_places = rs.getInt("nb_places");

	                Vehicle vehicle = new Vehicle();
	                
	                vehicle.setId(vehicleId);
	                vehicle.setConstructeur(vehicleConstructeur);
	                vehicle.setNb_places(vehicleNb_places);
	                
	                list_vehicle.add(vehicle);
	            }

			} catch (SQLException e) {
				throw new DaoException("Probleme lors de l'execution de find all de vehicle");
			}
			
			return list_vehicle;

		
	}
	
	/**
	 * Fonction qui permet de retourner le nombre de vehicles crees
	 * @return int
	 * @throws DaoException
	 */
	public int count() throws DaoException {
		try(	
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(COUNT_VEHICLES);
			)
		{
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("count");
			
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de l'execution de la fonction count() de vehicle");
		}
	}
	

}
