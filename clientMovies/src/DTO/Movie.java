package DTO;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "movieService")
@ApplicationScoped
public class Movie {
	int id;
	String name;
	int idDirector;

	public Movie(int id, String name, int idDirector) {
		super();
		this.id = id;
		this.name = name;
		this.idDirector = idDirector;
	}

	public Movie() {
		super();
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

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", idDirector=" + idDirector + "]";
	}

}
