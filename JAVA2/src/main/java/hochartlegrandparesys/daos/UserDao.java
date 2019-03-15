package hochartlegrandparesys.daos;


import java.util.List;

import hochartlegrandparesys.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UserDao {
	/**
	 * @return a list with all users
	 */
	public static List<User> listUsers(){
		List<User> listUsers = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			try (Statement stmt = connection.createStatement()) {
				try (ResultSet results = stmt
						.executeQuery("select * from users")) {
					while (results.next()) {
						User user = new User(results.getString("firstname"),results.getString("lastname")
								,results.getString("phone_number"),results.getString("address")
								,results.getString("email_address"),results.getLong("iduser")
								,results.getString("login"),results.getString("password"));
						
						listUsers.add(user);
					}
					connection.close();
					return listUsers;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}
	/**
	 * add a user to the database
	 */
	public static User addUser(User user) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			// Here we pass an option to tell the DB that we want to get the
			// generated keys back
			try (PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO users(lastname,firstname,phone_number,address,email_address,login,password) "
					+ "VALUES(?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS)) 
			{
				stmt.setString(1, user.getLastname());
				stmt.setString(2,user.getFirstname());
				stmt.setString(3,user.getPhoneNumber());
				stmt.setString(4,user.getAddress());
				stmt.setString(5,user.getEmailAddress());
				stmt.setString(6,user.getUsername());
				stmt.setString(7,user.getPassword());
					
				stmt.executeUpdate();
				// A little routine to grab the key and set it in our object
				try (ResultSet keys = stmt.getGeneratedKeys()) {
					keys.next();
					user.setIdUser(keys.getInt(1));
					connection.close();
					return user;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}
	/**
	 * delete a user depending of his iduser
	 */
	public static void deleteUser(int index) {
		try(Connection connection= DataSourceFactory.getDataSource().getConnection()) {
			try(PreparedStatement statement= connection.prepareStatement("DELETE FROM users WHERE iduser=?")) {
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
	 * delete all users
	 */
	public static void deleteAll() {
		try(Connection connection= DataSourceFactory.getDataSource().getConnection()) {
			try(PreparedStatement statement= connection.prepareStatement("DELETE FROM users")) {;
				statement.executeUpdate();
			}
			connection.close();
		}
		catch (SQLException e) {
			throw new RuntimeException("Oops", e);
		}
	}
	/**
	 * get an user with his username
	 */
	public static User getUserByUsername(String username) {
		User user = new User();
		try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM users WHERE login=?")){
				statement.setString(1, username);
				System.out.println("PreparedStatement is ok");
				try(ResultSet result = statement.executeQuery()){
					result.next();
					user.setFirstname(result.getString("firstname"));
					user.setLastname(result.getString("lastname"));
					user.setUsername(result.getString("login"));
					user.setPassword(result.getString("password"));
					user.setPhoneNumber(result.getString("phone_number"));
					user.setAddress(result.getString("address"));
					user.setEmailAddress(result.getString("email_address"));
					user.setIdUser(result.getLong("iduser"));
					
					connection.close();
				}
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Oops", e);
		}
		
		return user;
	}
	
}
