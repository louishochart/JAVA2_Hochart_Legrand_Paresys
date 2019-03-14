package hochartlegrandparesys.daos;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hochartlegrandparesys.models.Contact;

public class ContactDaoTestCase {
	
	int index1;
	int index2;

	@Before
	public void initDb() throws SQLException {
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement stmt1 = connection.createStatement();
		stmt1.executeUpdate("DELETE FROM contacts");

		PreparedStatement stmt2 = connection.prepareStatement(
				"INSERT INTO contacts(lastname,firstname,phone_number,address,email_address,birth_date,nickname,iduser) "
						+ "VALUES(?,?,?,?,?,?,?,?)");
		stmt2.setString(1, "lastname2");stmt2.setString(2, "firstname2");stmt2.setString(3, "phone2");stmt2.setString(4, "address2");stmt2.setString(5, "email2");stmt2.setDate(6, Date.valueOf("1000-10-10"));stmt2.setString(7, "nick2");
		stmt2.setLong(8, 1);
		stmt2.execute();
		
		PreparedStatement stmt3 = connection.prepareStatement(
				"INSERT INTO contacts(lastname,firstname,phone_number,address,email_address,birth_date,nickname,iduser) "
						+ "VALUES(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		stmt3.setString(1, "lastname1");stmt3.setString(2, "firstname1");stmt3.setString(3, "phone1");stmt3.setString(4, "address1");stmt3.setString(5, "email1");stmt3.setDate(6, Date.valueOf("1000-10-10"));stmt3.setString(7, "nick1");
		stmt3.setLong(8, 2);
		stmt3.execute();
		ResultSet keys1 = stmt3.getGeneratedKeys();
		keys1.next();
		index1 = keys1.getInt(1);
		
		
		PreparedStatement stmt4 = connection.prepareStatement(
				"INSERT INTO contacts(lastname,firstname,phone_number,address,email_address,birth_date,nickname,iduser) "
						+ "VALUES(?,?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		stmt4.setString(1, "lastname3");stmt4.setString(2, "firstname3");stmt4.setString(3, "phone3");stmt4.setString(4, "address3");stmt4.setString(5, "email3");stmt4.setDate(6, Date.valueOf("1000-10-10"));stmt4.setString(7, "nick3");
		stmt4.setLong(8, 3);
		stmt4.execute();
		ResultSet keys2 = stmt4.getGeneratedKeys();
		keys2.next();
		index2 = keys2.getInt(1);

		stmt4.close();
		stmt1.close();
		connection.close();
	}

	@Test
	public void shouldListContacts() throws SQLException {
		// WHEN we call our DAO to get contacts
		List<Contact> contacts = ContactDao.listContacts();
		// THEN our list should contains 3 items
		assertThat(contacts).hasSize(3).doesNotContainNull();
	}

	@Test
	public void shouldGetContactsCorrespondingToUserId() throws Exception{
		List<Contact> contacts = ContactDao.listContactsbyUserId(1);
		assertThat(contacts).hasSize(1).doesNotContainNull();
		Contact contact = contacts.get(0);
		assertThat(contact.getFirstname()).isEqualTo("firstname2");
		assertThat(contact.getLastname()).isEqualTo("lastname2");
		assertThat(contact.getNickname()).isEqualTo("nick2");
		assertThat(contact.getPhoneNumber()).isEqualTo("phone2");
		assertThat(contact.getAddress()).isEqualTo("address2");
		assertThat(contact.getEmailAddress()).isEqualTo("email2");
		assertThat(contact.getBirthdate()).isEqualTo(Date.valueOf("1000-10-10"));
		assertThat(contact.getIdUser()).isEqualTo(1);
	}
	
	@Test
	public void shouldAddContact() throws Exception {
		// GIVEN we have a Contact DAO ready to take orders and a contact to add
		Contact contact = new Contact("firstname", "lastname", "phone", "address", "email", "nick",
				Date.valueOf("1000-10-10"), 4);
		// WHEN we call our DAO to
		Contact newContact = ContactDao.addContact(contact);

		// THEN our user should have been persisted, and we should get back our
		// contact with an ID.

		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts WHERE firstname='firstname'");
		assertThat(resultSet.next()).isTrue();
		assertThat(resultSet.getInt("iduser")).isNotNull();
		assertThat(resultSet.getString("firstname")).isEqualTo("firstname");
		assertThat(resultSet.getString("lastname")).isEqualTo("lastname");
		assertThat(resultSet.getString("phone_number")).isEqualTo("phone");
		assertThat(resultSet.getString("address")).isEqualTo("address");
		assertThat(resultSet.getString("email_address")).isEqualTo("email");
		assertThat(resultSet.getString("nickname")).isEqualTo("nick");
		assertThat(resultSet.getDate("birth_date")).isEqualTo(Date.valueOf("1000-10-10"));
		assertThat(resultSet.getLong("iduser")).isEqualTo(4);

		assertThat(resultSet.next()).isFalse(); // only one contact persisted in DB
		resultSet.close();
		statement.close();
		connection.close();
		// Now check the contact that we got back
		assertThat(newContact).isNotNull(); // We got a film
		assertThat(newContact.getIdUser()).isNotNull(); // the film has an id

		// the contact that we got back is the same that the one we stored
		assertThat(newContact.getFirstname()).isEqualTo(contact.getFirstname());
		assertThat(newContact.getLastname()).isEqualTo(contact.getLastname());
		assertThat(newContact.getAddress()).isEqualTo(contact.getAddress());
		assertThat(newContact.getEmailAddress()).isEqualTo(contact.getEmailAddress());
		assertThat(newContact.getPhoneNumber()).isEqualTo(contact.getPhoneNumber());
		assertThat(newContact.getNickname()).isEqualTo(contact.getNickname());
		assertThat(newContact.getBirthdate()).isEqualTo(contact.getBirthdate());
		assertThat(newContact.getIdUser()).isEqualTo(contact.getIdUser());
	}

	@Test
	public void shouldDeleteUser() throws Exception {
		ContactDao.deleteContact(index2);
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts WHERE idcontact=" + index2);
		assertThat(resultSet.next()).isFalse();
	}

	@Test
	public void shouldDeleteAllUsers() throws Exception {
		ContactDao.deleteAll();
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");
		assertThat(resultSet.next()).isFalse();
	}
	
	@Test
	public void shouldUpdateContact() throws SQLException {
		Contact contact = new Contact("updtfirstname", "updtlastname", "updtphone", "updtaddress", "updtemail", "updtnick",
				Date.valueOf("2000-10-10"), 2);
		ContactDao.updateContact(index1, contact);
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts WHERE idcontact="+index1);
		assertThat(resultSet.next()).isTrue();
		assertThat(resultSet.getInt("iduser")).isNotNull();
		assertThat(resultSet.getString("firstname")).isEqualTo("updtfirstname");
		assertThat(resultSet.getString("lastname")).isEqualTo("updtlastname");
		assertThat(resultSet.getString("phone_number")).isEqualTo("updtphone");
		assertThat(resultSet.getString("address")).isEqualTo("updtaddress");
		assertThat(resultSet.getString("email_address")).isEqualTo("updtemail");
		assertThat(resultSet.getString("nickname")).isEqualTo("updtnick");
		assertThat(resultSet.getDate("birth_date")).isEqualTo(Date.valueOf("2000-10-10"));
		assertThat(resultSet.getLong("iduser")).isEqualTo(2);
	}
}
