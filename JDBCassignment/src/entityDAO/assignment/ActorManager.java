package entityDAO.assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.assignment.*;

public class ActorManager {

DataSource ds;
	
	public ActorManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Assignment4");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	//create actor
	public void createActor(Actor newActor){
		String sql = "insert into actor values (?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newActor.getid());
			statement.setString(2, newActor.getFirstName());
			statement.setString(3, newActor.getLastName());
			statement.setDate(4, (java.sql.Date) newActor.getDateOfBirth());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//retrieve all actors
	public List<Actor> readAllActors(){
		List<Actor> actors = new ArrayList<Actor>();
		String sql = "select * from actor";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Actor actor= new Actor();
				actor.setid(results.getString("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("LastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
				actors.add(actor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actors;
	}
	
	// retrieve an actor by id
	public Actor readActor(String actorId){
		Actor actor = new Actor();
		String sql = "select * from actor where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				actor.setid(result.getString("id"));
				actor.setFirstName(result.getString("firstName"));
				actor.setLastName(result.getString("LastName"));
				actor.setDateOfBirth(result.getDate("dateOfBirth"));
				return actor;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	
	// update an actor
	public void updateActor(String actorId, Actor actor){
		String sql = "update actor set firstName=?, lastName=?, dateOfBirth=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(2, actor.getFirstName());
			statement.setString(3, actor.getLastName());
			statement.setDate(4, (java.sql.Date) actor.getDateOfBirth());
			statement.setString(1, actorId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	
	//delete an actor
	public void deleteActor(String actorId){
		String sql = "delete from actor where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
					} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	}




