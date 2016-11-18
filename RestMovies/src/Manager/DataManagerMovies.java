package Manager;

import java.util.List;

import Entities.Movie;

public interface DataManagerMovies {

	// obtiene la lista actual de peliculas
	List<Movie> getMovies() throws Exception;

	// obtiene una pelicula usando el id
	Movie getMovie(Movie movie) throws Exception;

	// almacena una nueva pelicula
	void saveMovie(Movie movie) throws Exception;

	// actualiza una pelicula usando el id
	void updateMovie(Movie movie) throws Exception;

	// elimina una pelicula usando el id
	void deleteMovie(Movie movie) throws Exception;
}
