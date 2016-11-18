package Service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import Entities.Movie;
import Manager.DataManagerMoviesImpl;

@Path("movies")
public class ServicesMovies {
	DataManagerMoviesImpl DataManager = new DataManagerMoviesImpl();

	@GET
	@Produces("application/json")
	public Response getMovies() throws Exception {
		List<Movie> response = DataManager.getMovies();
		String responseMovies = new JSONArray(response).toString();
		return Response.status(200).entity(responseMovies).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMoviesById(@PathParam("id") int id) {
		Movie movie = new Movie();
		movie.setId(id);
		Movie movieRes = new Movie();
		try {
			movieRes = DataManager.getMovie(movie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = new JSONObject(movieRes).toString();
		return Response.status(200).entity(data).build();
	}
	
	@POST 
	public Response createMovie(@FormParam("name") String name,
										@FormParam("idDirector") int inDirector) throws Exception {
		if (name == null || name.isEmpty())
			return Response.status(400).entity("BAD REQUEST").build();
		else {
			Movie movie = new Movie();
			movie.setName(name);
			movie.setIdDirector(inDirector);
			DataManager.saveMovie(movie);
			return Response.status(201).entity("The movie:  " + name + " was saved successfull").build();

		}
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDirector(@FormParam("name") String name,
			                     @FormParam("id_director") int idDirector,
			                     @PathParam("id") int id) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setName(name);
		movie.setIdDirector(idDirector);
		try {
			DataManager.updateMovie(movie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = new JSONObject(movie).toString();
		return Response.status(200).entity(data).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMovie(@PathParam("id") int id) {
		Movie movie = new Movie();
		movie.setId(id);
		try {
			DataManager.deleteMovie(movie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = new JSONObject(movie).toString();
		return Response.status(200).entity(data).build();
	}
}
