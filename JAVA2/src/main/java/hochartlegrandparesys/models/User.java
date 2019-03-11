package hochartlegrandparesys.models;

public class User extends Person{
	
	/*
	 * Variables
	 */
	private long idUser;
	private String username;
	private String password;
	
	/*
	 * Constructor
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String firstname, String lastname, String phoneNumber, String address, String emailAddress
		, String username, String password) {
		super(firstname, lastname, phoneNumber, address, emailAddress);
		this.username = username;
		this.password = password;
	}
	
	public User(String firstname, String lastname, String phoneNumber, String address, String emailAddress
			, long idUser, String username, String password) {
		super(firstname, lastname, phoneNumber, address, emailAddress);
		this.idUser = idUser;
		this.username = username;
		this.password = password;
	}
	
	/*
	 * Getters and Setters
	 */
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username + ", password=" + password + ", toString()="
				+ super.toString() + "]";
	}
	

}
