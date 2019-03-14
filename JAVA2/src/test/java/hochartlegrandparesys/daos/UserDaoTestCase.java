package hochartlegrandparesys.daos;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hochartlegrandparesys.models.User;

public class UserDaoTestCase {
	
	int index;
	
	@Before
	public void initDb() throws SQLException {
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM users");
		stmt.executeUpdate("INSERT INTO users(lastname,firstname,phone_number,address,email_address,login,password) "
				+ "VALUES('lastname1','firstname1','phone1','address1','email1','login1','pwd1')");
		stmt.executeUpdate("INSERT INTO users(lastname,firstname,phone_number,address,email_address,login,password) "
				+ "VALUES('lastname2','firstname2','phone2','address2','email2','login2','pwd2')");
		PreparedStatement stmt2 = connection.prepareStatement(
				"INSERT INTO users(lastname,firstname,phone_number,address,email_address,login,password) "
				+ "VALUES(?,?,?,?,?,?,?)",
			Statement.RETURN_GENERATED_KEYS);
		stmt2.setString(1,"lastname3");
		stmt2.setString(2,"firstname3");
		stmt2.setString(3,"phone3");
		stmt2.setString(4,"address3");
		stmt2.setString(5,"email3");
		stmt2.setString(6,"login3");
		stmt2.setString(7,"pwd3");
		stmt2.execute();
		ResultSet keys = stmt2.getGeneratedKeys();
		keys.next();
		index = keys.getInt(1);
		
		stmt2.close();
		stmt.close();
		connection.close();
	}
	
	@Test
	public void shouldListUsers() throws SQLException {
		// WHEN we call our DAO to get users
		List<User> users= UserDao.listUsers();
		// THEN our list should contains 3 items
		assertThat(users).hasSize(3).doesNotContainNull();
	}
	
	@Test
	public void shouldAddUser() throws Exception {
		// GIVEN we have a User DAO ready to take orders and a user to add
		User user = new User("firstname","lastname","phone","address","email","username","password");
		// WHEN we call our DAO to
		User newUser = UserDao.addUser(user);
		
		// THEN our user should have been persisted, and we should get back our
		// user with an ID.

		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE firstname='firstname'");
		assertThat(resultSet.next()).isTrue();
		assertThat(resultSet.getInt("iduser")).isNotNull();
		assertThat(resultSet.getString("firstname")).isEqualTo("firstname");
		assertThat(resultSet.getString("lastname")).isEqualTo("lastname");
		assertThat(resultSet.getString("phone_number")).isEqualTo("phone");
		assertThat(resultSet.getString("address")).isEqualTo("address");
		assertThat(resultSet.getString("email_address")).isEqualTo("email");
		assertThat(resultSet.getString("login")).isEqualTo("username");
		assertThat(resultSet.getString("password")).isEqualTo("password");

		assertThat(resultSet.next()).isFalse(); // only one user persisted in DB
		resultSet.close();
		statement.close();
		connection.close();
		// Now check the user that we got back
		assertThat(newUser).isNotNull(); // We got a film
		assertThat(newUser.getIdUser()).isNotNull(); // the film has an id
		
		// the user that we got back is the same that the one we stored
		assertThat(newUser.getFirstname()).isEqualTo(user.getFirstname());
		assertThat(newUser.getLastname()).isEqualTo(user.getLastname());
		assertThat(newUser.getAddress()).isEqualTo(user.getAddress());
		assertThat(newUser.getEmailAddress()).isEqualTo(user.getEmailAddress());
		assertThat(newUser.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
		assertThat(newUser.getUsername()).isEqualTo(user.getUsername());
		assertThat(newUser.getPassword()).isEqualTo(user.getPassword());
	}
	
	@Test 
	public void shouldDeleteUser() throws Exception{
		UserDao.deleteUser(index);
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE iduser="+index);
		assertThat(resultSet.next()).isFalse();
	}
	
	@Test
	public void shouldDeleteAllUsers() throws Exception{
		UserDao.deleteAll();
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
		assertThat(resultSet.next()).isFalse();
	}
}
