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

import Entities.Director;
import Manager.DataManagerDirectorsImpl;

@Path("directors")
public class ServicesDirectors {
	DataManagerDirectorsImpl DataManager = new DataManagerDirectorsImpl();

	@GET
	@Produces("application/json")
	public Response getDirectors() throws Exception {
		List<Director> response = DataManager.getDirectors();
		String responseDirector = new JSONArray(response).toString();
		return Response.status(200).entity(responseDirector).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDirectorById(@PathParam("id") int id) {
		Director director = new Director();
		director.setId(id);
		Director directorRes = new Director();
		try {
			directorRes = DataManager.getDirector(director);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = new JSONObject(directorRes).toString();
		return Response.status(200).entity(data).build();
	}

	@POST
	public Response createDirector(@FormParam("name") String name, @FormParam("last_name") String lastName)
			throws Exception {
		if (name == null || name.isEmpty())
			return Response.status(400).entity("bad request").build();
		else {
			Director director = new Director();
			director.setName(name);
			director.setLastName(lastName);
			DataManager.saveDirector(director);
			return Response.status(201).entity("The director:  " + name + " " + lastName + " was saved successfull")
					.build();

		}
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDirector(@FormParam("name") String name,
			                     @FormParam("last_name") String last_name,
			                     @PathParam("id") int id) {
		Director director = new Director();
		director.setId(id);
		director.setName(name);
		director.setLastName(last_name);
		try {
			DataManager.updateDirector(director);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = new JSONObject(director).toString();
		return Response.status(200).entity(data).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDirector(@PathParam("id") int id) {
		Director director = new Director();
		director.setId(id);
		try {
			DataManager.deleteDirector(director);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = new JSONObject(director).toString();
		return Response.status(200).entity(data).build();
	}
}
