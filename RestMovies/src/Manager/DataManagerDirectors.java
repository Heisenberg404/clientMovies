package Manager;

import java.util.List;

import Entities.Director;

public interface DataManagerDirectors {

	// obtiene la lista actual de directores
	List<Director> getDirectors() throws Exception;

	// obtiene un director usando el id
	Director getDirector(Director director) throws Exception;

	// almacena un nuevo director
	void saveDirector(Director director) throws Exception;

	// actualiza un director usando el id
	void updateDirector(Director director) throws Exception;

	// elimina un director usando el id
	void deleteDirector(Director director) throws Exception;

	
}
