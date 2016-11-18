package Entities;

public class Movie {
	int id;
	String name;
	int idDirector;

	public Movie() {
		super();
	}

	public Movie(int id, String name, int idDirector) {
		super();
		this.id = id;
		this.name = name;
		this.idDirector = idDirector;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}

}
