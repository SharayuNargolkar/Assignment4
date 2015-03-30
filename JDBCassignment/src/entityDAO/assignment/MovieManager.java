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

public class MovieManager {

DataSource ds;
	
	public MovieManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Assignment4");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	// create a movie
	public void createMovie(Movie movie)
	{
		String sql = "insert into movie values (?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movie.getId());
			statement.setString(2, movie.getTitle());
			statement.setString(3, movie.getPosterimage());
			statement.setDate(4, (java.sql.Date) movie.getReleasedate());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	// retrieve all movies
	public List<Movie> AllMovies()
	{
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "select * from movie";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Movie movie = new Movie();
				movie.setId(results.getString("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterimage(results.getString("posterimage"));
				movie.setReleasedate(results.getDate("releasedate"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	// retrieve a movie by id
	public Movie readMovie(String movieid)
	{
		Movie movie = new Movie();
		
		String sql = "select * from movie where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				movie.setId(result.getString("id"));
				movie.setTitle(result.getString("title"));
				movie.setPosterimage(result.getString("posterimage"));
				movie.setReleasedate(result.getDate("releasedate"));
				return movie;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	// update a movie by id
	public void updateMovie(String movieid, Movie movie)
	{
		String sql = "update movie set title=?, posterimage=?, relesedate=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(2, movie.getTitle());
			statement.setString(3, movie.getPosterimage());
			statement.setDate(4, (java.sql.Date) movie.getReleasedate());
			statement.setString(1, movieid);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	// delete a movie by id
	public void deleteMovie(String movieid)
	{
		String sql = "delete from movie where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieid);
				} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}