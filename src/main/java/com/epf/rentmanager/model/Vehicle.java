package com.epf.rentmanager.model;  


public class Vehicle extends Object{
	private int id;
	private String constructeur;
	private int nb_places;
	
	//////////////////Constructeurs///////////////////

	public Vehicle() {}
	
	public Vehicle(int pid, String pconstructeur, int pnb_places){
		id = pid;
		constructeur = pconstructeur;
		nb_places = pnb_places;
	}
	
	public Vehicle( String pconstructeur, int pnb_places){
		constructeur = pconstructeur;
		nb_places = pnb_places;
	}
		
	public Vehicle(int pid) {
		id = pid;
	}
	
	////////////////Fonctions/////////////////////
	
	/** 
	 * Fonction qui verifie si la voiture possede entre 2 et 9 places
	 * @return boolean
	 */
    public boolean Nb_placesValide() {
    	
        boolean Nb_places = false;
        nb_places=this.getNb_places();
        
        if (nb_places>2 && nb_places<9) 
        {
        	Nb_places = true;
        }
        return Nb_places;
    }
	////////////////Getters et Setters///////////////////

    /** 
     * @return id
     */
	public int getId() {
		return id;
	}

    /** 
     * @param id
     */
	public void setId(int id) {
		this.id = id;
	}

    /** 
     * @return String
     */
	public String getConstructeur() {
		return constructeur;
	}

    /** 
     * @param constructeur
     */
	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}

    /** 
     * @return id
     */
	public int getNb_places() {
		return nb_places;
	}

    /** 
     * @param nb_places
     */
	public void setNb_places(int nb_places) {
		this.nb_places = nb_places;
	}
	
	/** 
	 * @return String
	 */
    @Override
	public String toString() {
		   return "id" + this.id 
		      + " : constructeur " + this.constructeur
		      + " : nombre de place " + this.nb_places;
	}

}
