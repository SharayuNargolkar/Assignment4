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

public class CastManager {

DataSource ds;
	
	public CastManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Assignment4");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	// Create a Cast
	public void createCast(Cast newCast){
		String sql = "insert into cast values (?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setString(2, newCast.getActorid());
			statement.setString(3, newCast.getMovieid());
			statement.setString(4, newCast.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//retrieve all casts
	public List<Cast> readAllCast(){
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast= new Cast();
				cast.setCharacterName(results.getString("charactername"));
				cast.setActorid(results.getString("actorid"));
				cast.setMovieid(results.getString("movieid"));
				cast.setId(results.getString("id"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;
	}
	
	//retrieve all casts with given actorid
	public List<Cast> readAllCastForActor(String actorId){
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast where actorid = ? ";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(2, actorId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				Cast cast= new Cast();
				cast.setCharacterName(results.getString("charactername"));
				cast.setActorid(results.getString("actorid"));
				cast.setMovieid(results.getString("movieid"));
				cast.setId(results.getString("id"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		return casts; 
	}

	//retrieve all casts with given movieid
	public List<Cast> readAllCastForMovie(String movieId){
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast where movieid = ? ";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(3, movieId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				Cast cast= new Cast();
				cast.setCharacterName(results.getString("charactername"));
				cast.setActorid(results.getString("actorid"));
				cast.setMovieid(results.getString("movieid"));
				cast.setId(results.getString("id"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		return casts; 
	}
	
	//retrieve a cast with given castid
	public Cast readCastForId(String castId){
		Cast cast= new Cast();
		String sql = "select * from cast where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				cast.setCharacterName(result.getString("charactername"));
				cast.setActorid(result.getString("actorid"));
				cast.setMovieid(result.getString("movieid"));
				cast.setId(result.getString("id"));
				return cast;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	//update cast
	public void updateCast(String castId, Cast newCast){
		String sql = "update cast set charactername=?, actorid=?, movieid=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setString(2, newCast.getActorid());
			statement.setString(3, newCast.getMovieid());
			statement.setString(4, castId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
// delete cast		
	public void deleteCast(String castId){
		String sql = "delete from cast where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(4, castId);
					} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	}
