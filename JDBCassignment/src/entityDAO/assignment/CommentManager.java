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

public class CommentManager {

DataSource ds;
	
	public CommentManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Assignment4");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	
	//create a comment
	public void createComment(Comment newComment){
		String sql = "insert into comment values (?,?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment.getComment());
			statement.setDate(2, (java.sql.Date) newComment.getDate());
			statement.setString(3, newComment.getUserName());
			statement.setString(4, newComment.getMovieid());
			statement.setString(4, newComment.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//retrieve all comments	
	public List<Comment> readAllComments(){
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment= new Comment();
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUserName(results.getString("username"));
				comment.setMovieid(results.getString("movieid"));
				comment.setId(results.getString("id"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	
	// // retrieve all comments with username given
	public List<Comment> readAllCommentsForUserName(String userName){
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment where userName = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(3, userName);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				Comment comment= new Comment();
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUserName(results.getString("username"));
				comment.setMovieid(results.getString("movieid"));
				comment.setId(results.getString("id"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		return comments; 
	}

	
	// retrieve all comments with given movieid
	public List<Comment> readAllCommentsForMovie(String movieId){
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment where movieId = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(4, movieId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				Comment comment= new Comment();
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUserName(results.getString("username"));
				comment.setMovieid(results.getString("movieid"));
				comment.setId(results.getString("id"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		return comments; 
	}
	
	// retrieve a comment given commentid
	public Comment readCommentForId(String commentId){
		Comment comment= new Comment();
		String sql = "select * from comment where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				comment.setComment(result.getString("comment"));
				comment.setDate(result.getDate("date"));
				comment.setUserName(result.getString("username"));
				comment.setMovieid(result.getString("movieid"));
				comment.setId(result.getString("id"));
				return comment;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	
	// update Comment
	public void updateComment(String commentId, Comment newComment){
		String sql = "update comment set comment=?, date=?, username=?, movieid=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			Comment comment = new Comment();
			statement.setString(1, comment.getComment());
			statement.setDate(2, (java.sql.Date) comment.getDate());
			statement.setString(3, comment.getUserName());
			statement.setString(4, comment.getMovieid());
			statement.setString(5, commentId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	
	//delete comment
	public void deleteComment(String commentId){
		String sql = "delete from comment where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(5, commentId);
					} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	}
