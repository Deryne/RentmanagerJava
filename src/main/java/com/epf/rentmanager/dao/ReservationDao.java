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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	public ReservationDao() {}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS = "SELECT COUNT(id) AS count FROM Reservation;";
	private static final String UPDATE_RESERVATION = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";

	/**
	 * Fonction qui permet de creer une reservation
	 * @param Reservation qui va etre cree
	 * @return int
	 * @throws DaoException
	 */
	public int create(Reservation reservation) throws DaoException {
		int id = 0;
		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY,
					id = Statement.RETURN_GENERATED_KEYS);
		){
			LocalDate debut = reservation.getDebut();
			LocalDate fin = reservation.getFin();

			pstmt.setInt(1, reservation.getClient_id());
			pstmt.setInt(2, reservation.getVehicle_id());
			pstmt.setDate(3, Date.valueOf(debut));
			pstmt.setDate(4, Date.valueOf(fin));

			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation de la reservation");
		}

	}

	/**
	 * Fonction qui permet de modifier une reservation
	 * @param Reservation qui va etre modifie
	 * @return int
	 * @throws DaoException
	 */
	public int modifier(Reservation reservation) throws DaoException {
		try (
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(UPDATE_RESERVATION);
		){
			
			LocalDate debut = reservation.getDebut();
			LocalDate fin = reservation.getFin();

			pstmt.setInt(1, reservation.getClient_id());
			pstmt.setInt(2, reservation.getVehicle_id());
			pstmt.setDate(3, Date.valueOf(debut));
			pstmt.setDate(4, Date.valueOf(fin));
			
			pstmt.setInt(5, reservation.getId());

			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la modification de la reservation");
		}

	}

	/**
	 * Fonction qui permet de supprimer une reservation
	 * @param Reservation qui va etre supprime
	 * @return int
	 * @throws DaoException
	 */
	public long delete(Reservation reservation) throws DaoException {
		
		try(
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);	
			) 
		{
		pstmt.setInt(1, reservation.getId());
			return pstmt.executeUpdate();	

		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la suppression de la reservation");
		}
	}
	
	/**
	 * Fonction qui permet d'afficher toutes les reservations crees
	 * @return List<Reservation>
	 * @throws DaoException
	 */
	public List<Reservation> findAll() throws DaoException {
		List<Reservation> list_reservations = new ArrayList<Reservation>();

		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);	
			)
		{
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int reservationid = rs.getInt("id");
				int reservationclient_id = rs.getInt("client_id");
				int reservationvehicle_id = rs.getInt("vehicle_id");
				LocalDate reservation_debut = rs.getDate("debut").toLocalDate();
				LocalDate reservation_fin = rs.getDate("fin").toLocalDate();

               Reservation reservation = new Reservation();
                
               reservation.setId(reservationid);
               reservation.setClient_id(reservationclient_id);
               reservation.setVehicle_id(reservationvehicle_id);
               reservation.setDebut(reservation_debut);
               reservation.setFin(reservation_fin);
                
               list_reservations.add(reservation);
            }

		} catch (SQLException e) {
			throw new DaoException("Probleme lors de l'execution de find all de reservation");
		}
		
		return list_reservations;
	}
	
	/**
	 * Fonction qui permet de trouvee une reservation par l'id du vehicle
	 * @parem int
	 * @return List<Reservation>
	 * @throws DaoException
	 */
	public List<Reservation> findByVehicle_id(int vehicule_id) throws DaoException {

		List<Reservation> result = new ArrayList<>();
		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			)
		{
			pstmt.setInt(1, vehicule_id);
			pstmt.execute();

			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) 
			{
				Reservation reservation = new Reservation();
				reservation.setId(resultSet.getInt("id"));
				reservation.setClient_id(resultSet.getInt("client_id"));
				reservation.setVehicle_id(vehicule_id);
				reservation.setDebut(resultSet.getDate("debut").toLocalDate());
				reservation.setFin(resultSet.getDate("fin").toLocalDate());
				
				result.add(reservation);
			}

		} catch (SQLException e) {
			throw new DaoException("Probleme lors de l'execution de find id de reservation");
		}
		return result;
	}
	
	/**
	 * Fonction qui permet de trouvee une reservation par l'id du client
	 * @param int
	 * @return List<Reservation>
	 * @throws DaoException
	 */
	public List<Reservation> findByClient_id(int client_id) throws DaoException {

		List<Reservation> result = new ArrayList<>();
		try (
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			)
		{
			pstmt.setInt(1, client_id);
			pstmt.execute();

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Reservation reservation = new Reservation();
				reservation.setId(resultSet.getInt("id"));
				reservation.setClient_id(client_id);
				reservation.setVehicle_id(resultSet.getInt("vehicle_id"));
				reservation.setDebut(resultSet.getDate("debut").toLocalDate());
				reservation.setFin(resultSet.getDate("fin").toLocalDate());
				result.add(reservation);
			}

		} catch (SQLException e) {
			throw new DaoException("Probleme lors de find by id client");
		}

		return result;

	}
	
	/**
	 * Fonction qui permet de trouvee une reservation par l'id
	 * @return Optionnal<Reservation>
	 * @throws DaoException
	 */
	public Optional<Reservation> findById(int id) throws DaoException {
		Reservation reservation = new Reservation();

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);
			pstmt.setInt(1, id);
			pstmt.execute();

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				reservation.setId(id);
				reservation.setClient_id(resultSet.getInt("client_id"));
				reservation.setVehicle_id(resultSet.getInt("vehicle_id"));
				reservation.setDebut(resultSet.getDate("debut").toLocalDate());
				reservation.setFin(resultSet.getDate("fin").toLocalDate());
			}
			return Optional.of(reservation);			

		} catch (SQLException e) {
			throw new DaoException("Probleme lors de l'execution de find id de la reservation");
		}
	}
	
	/**
	 * Fonction qui permet de retourner le nombre de reservations creees
	 * @return int
	 * @throws DaoException
	 */
	public int count() throws DaoException {
		try(
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(COUNT_RESERVATIONS);
			)
		{
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("count");
			
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de l'execution de la fonction count() de reservation");
		}

	}
	
}
