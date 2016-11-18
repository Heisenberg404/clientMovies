package Manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import DBConnection.ConnectionDatabase;
import Dao.TransactionDaoJdbc;
import Entities.Movie;

public class DataManagerMoviesImpl extends ConnectionDatabase implements DataManagerMovies {
	TransactionDaoJdbc transac = new TransactionDaoJdbc();

	@Override
	public List<Movie> getMovies() throws Exception {
		List<Movie> moviesList = new ArrayList<Movie>();
		Connection connection = (Connection) this.getConnection();
		moviesList = transac.getMovies(connection);
		return moviesList;
	}

	@Override
	public void saveMovie(Movie movie) throws Exception {
		Connection connection = (Connection) this.getConnection();
		transac.saveMovie(connection, movie);
	}

	@Override
	public Movie getMovie(Movie movie) throws Exception {
		Movie movieInfo = new Movie();
		Connection connection = (Connection) this.getConnection();
		try {
			movieInfo = transac.getMovieById(connection, movie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movieInfo;
	}

	@Override
	public void updateMovie(Movie movie) throws Exception {
		Connection connection = (Connection) this.getConnection();		
		try {
			transac.updateMovie(connection, movie);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteMovie(Movie movie) throws Exception {
		Connection connection = (Connection) this.getConnection();
		try {
			transac.deleteMovie(connection, movie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
