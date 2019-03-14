package hochartlegrandparesys.models;

public class Person {

	/*
	 * Variables
	 */
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private String address;
	private String emailAddress;
	
	 
	public Person() {
		super();
	}
	public Person(String firstname, String lastname, String phoneNumber, String address, String emailAddress) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.emailAddress = emailAddress;
	}
	/*
	 * Getters and Setters
	 */
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Override
	public String toString() {
		return "Person [firstname=" + firstname + ", lastname=" + lastname + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", emailAddress=" + emailAddress + "]";
	}
	
	
	
}
