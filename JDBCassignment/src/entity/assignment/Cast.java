package entity.assignment;

public class Cast {
	private String characterName;
	private String actorid;
	private String movieid;
	private String id;
	
	
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getActorid() {
		return actorid;
	}
	public void setActorid(String actorid) {
		this.actorid = actorid;
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
	
	public Cast(){
		super();
	}
	
	public Cast(String characterName, String actorid, String movieid, String id) {
		super();
		this.characterName = characterName;
		this.actorid = actorid;
		this.movieid = movieid;
		this.id = id;
	}
	
		
}
