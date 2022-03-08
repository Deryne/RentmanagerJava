package com.epf.rentmanager.model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation extends Object{
	private int id;
	private int client_id;
	private int vehicle_id;
	private LocalDate debut; 
	private LocalDate fin; 
	
	//////////////////Constructeurs///////////////////

	public Reservation() {}
	
	public Reservation(int pid, int pclient_id, int pvehicle_id, LocalDate pdebut, LocalDate pfin) {
		id = pid;
		client_id = pclient_id;
		vehicle_id = pvehicle_id;
		debut = pdebut;
		fin= pfin;
	}
	
	public Reservation(int pclient_id, int pvehicle_id, LocalDate pdebut, LocalDate pfin) {
		client_id = pclient_id;
		vehicle_id = pvehicle_id;
		debut = pdebut;
		fin= pfin;
	}
	
	public Reservation(int pid) {
		id = pid;
	}

	////////////////Fonctions/////////////////////
	
	/** 
	 * Fonction qui calcule la duree de la location
	 * @return int
	 */
	public int Duree() {
        int nb_jours = (int) ChronoUnit.DAYS.between(this.getDebut(), this.getFin());
        return nb_jours;
    }
	
	/** 
	 * Fonction qui verifie si la reservation ne depasse pas 7 jours
	 * @return boolean
	 */
    public boolean ResaTropLongue() {
        boolean ResaLongue = false;
        if (this.Duree() > 7) {
        	ResaLongue = true;
        }
        return ResaLongue;
    }

    
	////////////////Getters et Setters///////////////////

    /**
     * @return id
     */
	public int getId() {
		return this.id;
	}

    /**
     * @param id
     */
	public void setId(int id) {
		this.id = id;
	}

    /**
     * @return client_id
     */
	public int getClient_id() {
		return this.client_id;
	}

    /**
     * @param client_id
     */
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

    /**
     * @return vehicle_id
     */
	public int getVehicle_id() {
		return this.vehicle_id;
	}

    /**
     * @param vehicle_id
     */
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

    /**
     * @return LocalDate
     */
	public LocalDate getDebut() {
		return this.debut;
	}

    /**
     * @param debut
     */
	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

    /**
     * @return LocalDate
     */
	public LocalDate getFin() {
		return this.fin;
	}

    /**
     * @param fin
     */
	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

    /**
     * @return String
     */
    @Override
	public String toString() {
		   return "id" + this.id 
		      + " : client id " + this.client_id
		      +  " : vehicle id " + this.vehicle_id 
			  + " : debut " + this.debut
			  + " : fin " + this.fin;
	}
	
}
