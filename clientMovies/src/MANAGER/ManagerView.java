package MANAGER;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DTO.Director;
import DTO.Movie;

@ManagedBean
@ViewScoped
public class ManagerView implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Movie> movies;
	private List<Director> directors;
	REQUEST.Request req = new REQUEST.Request();
	Director directorSelected;
	Movie movieSelected;
    
	
    @PostConstruct
    public void init() {    	
    	movies = req.getListMovies();
    	directors = req.getListDirectors();
    }


	public List<Movie> getMovies() {
		return movies;
	}


	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}


	public List<Director> getDirectors() {
		return directors;
	}


	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}
 
	public void addMovie(Movie movie){
		int response;
		response = req.createMovie(movie);
		movies.clear();
		movies = req.getListMovies();
	}
    
	public void addDirector(Director director){
		int response;
		response = req.createDirector(director);
		directors.clear();
		directors = req.getListDirectors();
	}
	
	public void removeMovie(Movie movie){
		String id = String.valueOf(movie.getId());
		req.deleteMovie(id);
		movies.clear();
		movies = req.getListMovies();
	}
	
	public void removeDirector(Director director){
		String id = String.valueOf(director.getId());
		req.deleteDirector(id);
		directors.clear();
		directors = req.getListDirectors();
	}


	public Director getDirectorSelected() {
		return directorSelected;
	}


	public void setDirectorSelected(Director directorSelected) {
		this.directorSelected = directorSelected;
	}


	public Movie getMovieSelected() {
		return movieSelected;
	}


	public void setMovieSelected(Movie movieSelected) {
		this.movieSelected = movieSelected;
	}
    
	public void updateMovie(Movie movie){
		int response;
		response = req.updateMovie(movie);
		movies.clear();
		movies = req.getListMovies();
	}
	public void updateDirector(Director director){
		int response;
		response = req.updateDirector(director);
		directors.clear();
		directors = req.getListDirectors();
	}
	
	public void goToSearchDirectors(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("searchDirectors.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	public void goToSearchMovies(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("searchMovies.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void backHome(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
