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
	
	public List<Contact> listUsers(){
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
					return listcontacts;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}
	
	public List<Contact> listContactsbyUserId(Long userId) {
		List<Contact> byUser = new ArrayList<Contact>();
		try (Connection cnx = DataSourceFactory.getDataSource().getConnection()) {
			try (PreparedStatement stmt = cnx.prepareStatement(
					"SELECT * FROM contact  WHERE idusers.name = ?")) {
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
					return byUser;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}
	public Contact addContact(Contact contact) {
		try (Connection cnx = DataSourceFactory.getDataSource().getConnection()) {
			// Here we pass an option to tell the DB that we want to get the
			// generated keys back
			try (PreparedStatement stmt = cnx.prepareStatement(
					"INSERT INTO users(lastname,firstname,phone_number,address,email_address,birth_date,nickname,iduser) "
							+ "VALUES(?,?,?,?,?,?,?)",
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
					return contact;
				}
			}
		} 
		catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}
}
