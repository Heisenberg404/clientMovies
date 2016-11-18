package Dao;

import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;

import Entities.Director;
import Entities.Movie;

public interface TransactionDao {

	// obtiene una lista de datos con todas las entidades para mostrar
	List<Movie> getMovies(Connection connection) throws Exception;

	// obtiene una lista de datos con todas las entidades para mostrar
	List<Director> getDirectors(Connection connection) throws Exception;

	// almacena un nuevo director
	void saveDirector(Connection connection, Director director) throws SQLException;

	// almacena una nueva pelicula
	void saveMovie(Connection connection, Movie movie) throws SQLException;

	// consulta un director por medio del id
	Director getDirectorById(Connection connection, Director director) throws SQLException;

	// consulta una pelicula por medio del id
	Movie getMovieById(Connection connection, Movie movie) throws SQLException;

	// actualiza un director
	void updateDirector(Connection connection, Director director) throws SQLException;

	// elimina un director
	void deleteDirector(Connection connection, Director director) throws SQLException;

	// actualiza una pelicula
	void updateMovie(Connection connection, Movie movie) throws SQLException;

	// elimina una pelicula
	void deleteMovie(Connection connection, Movie movie) throws SQLException;
}
