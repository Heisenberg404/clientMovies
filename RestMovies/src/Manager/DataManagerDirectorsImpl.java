package Manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import DBConnection.ConnectionDatabase;
import Dao.TransactionDaoJdbc;
import Entities.Director;


public class DataManagerDirectorsImpl extends ConnectionDatabase implements DataManagerDirectors {
	TransactionDaoJdbc transac = new TransactionDaoJdbc();

	
	@Override
	public List<Director> getDirectors() throws Exception {
		List<Director> directorsList = new ArrayList<Director>();
		Connection connection = (Connection) this.getConnection();
		directorsList = transac.getDirectors(connection);
		return directorsList;
	}
	
	@Override
	public void saveDirector(Director director) throws Exception {
		Connection connection = (Connection) this.getConnection();
		transac.saveDirector(connection, director);
	}

	@Override
	public Director getDirector(Director director) throws Exception {
		Director directorInfo = new Director();
		Connection connection = (Connection) this.getConnection();
		try {
			directorInfo = transac.getDirectorById(connection, director);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return directorInfo;
	}

	@Override
	public void updateDirector(Director director) throws Exception {
		Connection connection = (Connection) this.getConnection();		
		try {
			transac.updateDirector(connection, director);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteDirector(Director director) throws Exception {
		Connection connection = (Connection) this.getConnection();
		try {
			transac.deleteDirector(connection, director);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
