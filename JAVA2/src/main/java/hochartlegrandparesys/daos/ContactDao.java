package hochartlegrandparesys.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hochartlegrandparesys.models.Contact;


public class ContactDao {

	/**
	 * @return a list with all contacts
	 */
	public static List<Contact> listContacts(){
		List<Contact> listcontacts = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			try (Statement stmt = connection.createStatement()) {
				try (ResultSet results = stmt
						.executeQuery("select * from contacts")) {
					while (results.next()) {
						Contact contact = new Contact(results.getString("firstname"),results.getString("lastname")
								,results.getString("phone_number"),results.getString("address")
								,results.getString("email_address"),results.getLong("idcontact")
								,results.getString("nickname"),results.getDate("birth_date")
								,results.getLong("idUser"));
						
						listcontacts.add(contact);
					}
					connection.close();
					return listcontacts;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}

	/**
	 * @return a list with all contacts linked with the given userId
	 */
	public static List<Contact> listContactsbyUserId(int userId) {
		List<Contact> byUser = new ArrayList<Contact>();
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			try (PreparedStatement stmt = connection.prepareStatement(
					"SELECT * FROM contacts WHERE iduser = ?")) {
				stmt.setLong(1, userId);
				try (ResultSet results = stmt.executeQuery()) {
					while (results.next()) {
						Contact contact = new Contact(results.getString("firstname"),results.getString("lastname")
								,results.getString("phone_number"),results.getString("address")
								,results.getString("email_address"),results.getLong("idcontact")
								,results.getString("nickname"),results.getDate("birth_date")
								,results.getLong("idUser"));		
						byUser.add(contact);
					}
					connection.close();
					return byUser;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}

	/**
	 * add a contact to the database
	 */
	public static Contact addContact(Contact contact) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			// Here we pass an option to tell the DB that we want to get the
			// generated keys back
			try (PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO contacts(lastname,firstname,phone_number,address,email_address,birth_date,nickname,iduser) "
							+ "VALUES(?,?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS)) 
			{
				stmt.setString(1,contact.getLastname());
				stmt.setString(2,contact.getFirstname());
				stmt.setString(3,contact.getPhoneNumber());
				stmt.setString(4,contact.getAddress());
				stmt.setString(5,contact.getEmailAddress());
				stmt.setDate(6,contact.getBirthdate());
				stmt.setString(7,contact.getNickname());
				stmt.setLong(8,contact.getIdUser());
				stmt.executeUpdate();
				// A little routine to grab the key and set it in our object
				try (ResultSet keys = stmt.getGeneratedKeys()) {
					keys.next();
					contact.setIdContact(keys.getInt(1));
					connection.close();
					return contact;
				}
			}
		} 
		catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}
	
	/**
	 * delete a contact depending of his idcontact
	 */
	public static void deleteContact(int index) {
		try(Connection connection= DataSourceFactory.getDataSource().getConnection()) {
			try(PreparedStatement statement= connection.prepareStatement("DELETE FROM contacts WHERE idcontact=?")) {
				statement.setInt(1, index);
				statement.executeUpdate();
			}
			connection.close();
		}
		catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}
	
	/**
	 * delete all contacts
	 */
	public static void deleteAll() {
		try(Connection connection= DataSourceFactory.getDataSource().getConnection()) {
			try(PreparedStatement statement= connection.prepareStatement("DELETE FROM contacts")) {;
				statement.executeUpdate();
			}
			connection.close();
		}
		catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}
}
