package hochartlegrandparesys.models;

import java.sql.Date;

public class Contact extends Person {
	
	/*
	 * Variables
	 */
	private long idContact;
	private long idUser;
	private String nickname;
	private Date birthdate;
	
	/*
	 * Constructor
	 */
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(String firstname, String lastname, String phoneNumber
			, String address, String emailAddress,long idContact
			, String nickname, Date birthdate, long idUser) {
		super(firstname, lastname, phoneNumber, address, emailAddress);
		this.idContact = idContact;
		this.nickname = nickname;
		this.birthdate = birthdate;
		this.idUser = idUser;
	}



	/*
	 * Getters and Setters
	 */
	public long getIdContact() {
		return idContact;
	}

	public void setIdContact(long idContact) {
		this.idContact = idContact;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

}
