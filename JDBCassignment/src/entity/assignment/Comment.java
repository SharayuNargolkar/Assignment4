package entity.assignment;

import java.util.Date;

public class Comment {
	private String comment;
	private Date date;
	private String username;
	private String movieid;
	private String id;


public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

public String getUserName() {
	return username;
}
public void setUserName(String username) {
	this.username = username;
}
public String getMovieid() {
	return movieid;
}
public void setMovieid(String movieid) {
	this.movieid = movieid;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Comment(){
	super();
	}


public Comment(String comment, Date date, String username, String movieid, String id) {
	super();
	this.comment = comment;
	this.date = date;
	this.username = username;
	this.movieid = movieid;
	this.id = id;
}

}
