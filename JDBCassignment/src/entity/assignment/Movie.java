package entity.assignment;

import java.util.Date;

public class Movie {
	private String Id;
	private String title;
	private String posterimage;
	private Date releasedate;
	
	
	public String getId() {
		return Id;
	}
	
	public void setId(String i) {
		this.Id = i;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPosterimage() {
		return posterimage;
	}
	public void setPosterimage(String posterimage) {
		this.posterimage = posterimage;
	}
	
	public Date getReleasedate() {
		return releasedate;
	}
	
	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public Movie() {
		super();
	}
	
	public Movie(int id, String title, String poster, String movieId, Date date) {
		super();
		this.Id = movieId;
		this.title = title;
		this.posterimage = poster;
		this.releasedate = date;
	}

}
