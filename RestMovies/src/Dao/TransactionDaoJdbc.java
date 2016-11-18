package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Entities.Director;
import Entities.Movie;

public class TransactionDaoJdbc implements TransactionDao {

	List<Movie> movies = new ArrayList<>();
	List<Director> directors = new ArrayList<>();
	protected PreparedStatement preparedStatement;
	protected ResultSet resultSet;

	@Override
	public List<Movie> getMovies(Connection connection) throws Exception {
		preparedStatement = null;
		resultSet = null;
		preparedStatement = (PreparedStatement) connection.prepareStatement("select * from movies");

		try {
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Movie movie = new Movie();
				movie.setId(resultSet.getInt("id"));
				movie.setName(resultSet.getString("name"));
				movie.setIdDirector(resultSet.getInt("id_director"));
				movies.add(movie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}

	@Override
	public List<Director> getDirectors(Connection connection) throws Exception {
		preparedStatement = null;
		resultSet = null;
		preparedStatement = (PreparedStatement) connection.prepareStatement("select * from directors");

		try {
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Director director = new Director();
				director.setId(resultSet.getInt("id"));
				director.setName(resultSet.getString("name"));
				director.setLastName(resultSet.getString("last_name"));
				directors.add(director);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return directors;
	}

	@Override
	public void saveDirector(Connection connection, Director director) throws SQLException {

		preparedStatement = null;
		resultSet = null;
		int index = 1;
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("INSERT INTO directors (name, last_name) VALUES (?,?)");
		preparedStatement.setString(index++, director.getName());
		preparedStatement.setString(index++, director.getLastName());
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	@Override
	public void saveMovie(Connection connection, Movie movie) throws SQLException {

		preparedStatement = null;
		resultSet = null;
		int index = 1;
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("INSERT INTO movies (name, id_director) VALUES (?,?)");
		preparedStatement.setString(index++, movie.getName());
		preparedStatement.setInt(index++, movie.getIdDirector());
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	@Override
	public Director getDirectorById(Connection connection, Director director) throws SQLException {
		Director directorRes = new Director();
		int index = 1;
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("SELECT id, name, last_name FROM directors WHERE id = ?");
		preparedStatement.setInt(index++, director.getId());
		try {
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				directorRes.setId(resultSet.getInt("id"));
				directorRes.setName(resultSet.getString("name"));
				directorRes.setLastName(resultSet.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return directorRes;
	}

	@Override
	public Movie getMovieById(Connection connection, Movie movie) throws SQLException {
		Movie movieRes = new Movie();
		int index = 1;
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("SELECT id, name, id_director FROM movies WHERE id = ?");
		preparedStatement.setInt(index++, movie.getId());
		try {
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				movieRes.setId(resultSet.getInt("id"));
				movieRes.setName(resultSet.getString("name"));
				movieRes.setIdDirector(resultSet.getInt("id_director"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return movieRes;
	}

	@Override
	public void updateDirector(Connection connection, Director director) throws SQLException {
		int index = 1;
		preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE directors SET name = ?, last_name = ? WHERE id = ?");
		preparedStatement.setString(index++, director.getName());
		preparedStatement.setString(index++, director.getLastName());
		preparedStatement.setInt(index++, director.getId());
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	@Override
	public void deleteDirector(Connection connection, Director director) throws SQLException {
		int index = 1;
		preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM directors WHERE id = ?");
		preparedStatement.setInt(index++, director.getId());
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	@Override
	public void updateMovie(Connection connection, Movie movie) throws SQLException {
		int index = 1;
		preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE movies SET name = ?, id_director = ? WHERE id = ?");
		preparedStatement.setString(index++, movie.getName());
		preparedStatement.setInt(index++, movie.getIdDirector());
		preparedStatement.setInt(index++, movie.getId());
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}

	@Override
	public void deleteMovie(Connection connection, Movie movie) throws SQLException {
		int index = 1;
		preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM movies WHERE id = ?");
		preparedStatement.setInt(index++, movie.getId());
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
}
