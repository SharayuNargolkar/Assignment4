package entityDAO.assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.assignment.*;

public class UserManager {

		DataSource ds;
		
		public UserManager()
		{
		  try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Assignment4");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
		}
		
		// create a user
		public User createUser(User user)
		{
			String sql = "insert into user values (?,?,?,?,?,?)";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, user.getFirstName());
				statement.setString(2, user.getLastName());
				statement.setString(3, user.getUsername());
				statement.setString(4, user.getPassword());
				statement.setString(5, user.getEmail());
				statement.setDate(6, (java.sql.Date) user.getDateOfBirth());
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		// retrieve all users
		public List<User> readAllUsers()
		{
			List<User> Users = new ArrayList<User>();
			String sql = "select * from user";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next())
				{
					User user = new User();
					user.setFirstName(results.getString("firstName"));
					user.setLastName(results.getString("lastName"));
					user.setUsername(results.getString("username"));
					user.setPassword(results.getString("password"));
					user.setEmail(results.getString("email"));
					user.setDateOfBirth(results.getDate("dateOfBirth"));
					Users.add(user);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Users;
		}
		
		// retrieve a user
				public User readUser(String username){
					String sql = "select * from user where userName = ?";
					try {
						Connection connection = ds.getConnection();
						PreparedStatement statement = connection.prepareStatement(sql);
						ResultSet results = statement.executeQuery();
						while(results.next())
						{
							String FirstName = results.getString("firstName");
							String LastName = results.getString("lastName");
							String Username = results.getString("username");
							String Password = results.getString("password");
							String Email = results.getString("email");
							Date date = results.getDate(6);
							User user_req = new User(FirstName, LastName, Username, Password, Email, date);
							return user_req;
							}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
		
		// update a user by username
		public void updateUser(String username, User user)
		{
			String sql = "update user set id=?, firstName=?, lastName=?, password=?, email=? where userName=?";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, user.getFirstName());
				statement.setString(2, user.getLastName());
				statement.setString(4, user.getPassword());
				statement.setString(5, user.getEmail());
				statement.setDate(6, (java.sql.Date) user.getDateOfBirth());
				statement.setString(3,username);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
		
		// delete a user by id
		public void deleteUser(String username)
		{
			String sql = "delete from user where userName=?";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(4, username);
				statement.execute();
						} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
