package com.epf.rentmanager.model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;  

public class Client extends Object {
	private int id;
	private String name;
	private String lastname;
	private String email;
	private LocalDate birthdate; 
	
	//////////////////Constructeurs///////////////////
	
	public Client() {}
	
	public Client(int pid, String pname, String plastname, String pemail, LocalDate pbirthdate) {
		id = pid;
		name = pname;
		lastname = plastname;
		email = pemail;
		birthdate = pbirthdate;
	}
	
	public Client(String pname, String plastname, String pemail, LocalDate pbirthdate) {
		name = pname;
		lastname = plastname;
		email = pemail;
		birthdate = pbirthdate;
	}
	
	public Client(int pid) {
		id = pid;
	}
	
	////////////////Fonctions/////////////////////
	
	/** 
	 * Fonction qui calcule l'age du client
	 * @return int
	 */
	public int getAge(Client client) {
		 LocalDate today = LocalDate.now();
		 long duration = ChronoUnit.YEARS.between(client.getBirthdate(), today);
		 return (int)duration;
	}
	
	/** 
	 * Fonction qui retourne true si l'age du client est => 18 ans
	 * @return boolean
	 */
	public boolean isLegal(Client client) {
	       return this.getAge(client) >= 18;
	}
	
	/** 
	 * Function qui vérifie si le prenom du client est long de plus de 3 caracteres
	 * @return boolean
	 */
	public boolean LenghtName() {
		return this.getName().length() >= 3;
	}
	
	/** 
	 * Function qui vérifie si le nom du client est long de plus de 3 caracteres
	 * @return boolean
	 */
	public boolean LenghtLastname() {
		return this.getLastname().length() >= 3;
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
	public String getName() {
		return name;
	}

	/** 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * @return String
	 */
	public String getLastname() {
		return lastname;
	}

	/** 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/** 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/** 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** 
	 * @return LocalDate
	 */	
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/** 
	 * @param birthdate
	 */
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		   return "id" + this.id 
		      + " : name " + this.name
		      +  " : lastname " + this.lastname
			  + " : email " + this.email
			  + " : birthdate " + this.birthdate +" | ";
	}
	
}
