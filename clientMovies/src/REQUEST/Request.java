package REQUEST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.primefaces.json.JSONArray;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import DTO.Director;
import DTO.Movie;

public class Request implements Serializable {

	List<Movie> movies = new ArrayList<Movie>();
	List<Director> directors = new ArrayList<Director>();
	private static final long serialVersionUID = 1L;
	
	public List<Movie> getListMovies() {
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/RestMovies").build());
		// getting JSON data
		System.out.println(service.path("movies").accept(MediaType.APPLICATION_JSON).get(String.class));
		JSONArray json = new JSONArray(service.path("movies").accept(MediaType.APPLICATION_JSON).get(String.class));

		for (int i = 0; i < json.length(); i++) {
			Movie movie = new Movie();
			movie.setId(json.getJSONObject(i).getInt("id"));
			movie.setIdDirector(json.getJSONObject(i).getInt("idDirector"));
			movie.setName(json.getJSONObject(i).getString("name"));
			movies.add(movie);
		}
		return movies;
			 
	}
	public List<Director> getListDirectors() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/RestMovies").build());
		// getting JSON data
		System.out.println(service.path("movies").accept(MediaType.APPLICATION_JSON).get(String.class));
		JSONArray json = new JSONArray(service.path("directors").accept(MediaType.APPLICATION_JSON).get(String.class));

		for (int i = 0; i < json.length(); i++) {
			Director director = new Director();
			director.setId(json.getJSONObject(i).getInt("id"));
			director.setName(json.getJSONObject(i).getString("name"));
			director.setLastName(json.getJSONObject(i).getString("lastName"));
			directors.add(director);
		}
		return directors;
			 
	}
	
	public int createDirector(Director director) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		ClientResponse response = null;
		try {
			WebResource resource = client
					.resource(UriBuilder.fromUri("http://localhost:8080/RestMovies").path("directors").build());
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("name", String.valueOf(director.getName()));
			formData.add("last_name", String.valueOf(director.getLastName()));

			// ws preparado para recibir solo @FormParam usa
			// APPLICATION_FORM_URLENCODED_TYPE

			response = resource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
			// JSONObject objO = response.getType(MediaType.APPLICATION_JSON);
			System.out.println(response);

		} catch (Exception e) {
			System.out.println(e);
		}
		return response.getStatus();

	}
	
	public int createMovie(Movie movie) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		ClientResponse response = null;
		try {
			WebResource resource = client
					.resource(UriBuilder.fromUri("http://localhost:8080/RestMovies").path("movies").build());
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("name", String.valueOf(movie.getName()));
			formData.add("idDirector", String.valueOf(movie.getIdDirector()));

			// ws preparado para recibir solo @FormParam usa
			// APPLICATION_FORM_URLENCODED_TYPE

			response = resource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
			// JSONObject objO = response.getType(MediaType.APPLICATION_JSON);
			System.out.println(response);

		} catch (Exception e) {
			System.out.println(e);
		}
		return response.getStatus();

	}
	
	public int deleteMovie(String id){
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		ClientResponse response = null;
		WebResource resource = client.resource(UriBuilder.fromUri("http://localhost:8080/RestMovies").build());
		// getting JSON data
		System.out.println(resource.path("movies").path(id).delete(String.class));
		response = resource.path("movies").path(id).delete(ClientResponse.class);
		return response.getStatus();
	}
	
	
	
	
	
	public int deleteDirector(String id){
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		ClientResponse response = null;
		WebResource resource = client.resource(UriBuilder.fromUri("http://localhost:8080/RestMovies").build());
		// getting JSON data
		System.out.println(resource.path("directors").path(id).delete(String.class));
		response = resource.path("directors").path(id).delete(ClientResponse.class);
		return response.getStatus();
	}
	
	public int updateDirector(Director director) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		ClientResponse response = null;
		try {
			WebResource resource = client.resource(UriBuilder.fromUri("http://localhost:8080/RestMovies").build());
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("name", String.valueOf(director.getName()));
			formData.add("last_name", String.valueOf(director.getLastName()));

			// ws preparado para recibir solo @FormParam usa
			// APPLICATION_FORM_URLENCODED_TYPE
			//response = resource.path("directors").path(id).delete(ClientResponse.class);
			response = resource.path("directors").path(String.valueOf(director.getId())).put(ClientResponse.class, formData);
			// JSONObject objO = response.getType(MediaType.APPLICATION_JSON);
			System.out.println(response);

		} catch (Exception e) {
			System.out.println(e);
		}
		return response.getStatus();

	}
	public int updateMovie(Movie movie) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		ClientResponse response = null;
		try {
			WebResource resource = client.resource(UriBuilder.fromUri("http://localhost:8080/RestMovies").build());
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("name", String.valueOf(movie.getName()));
			formData.add("id_director", String.valueOf(movie.getIdDirector()));

			// ws preparado para recibir solo @FormParam usa
			// APPLICATION_FORM_URLENCODED_TYPE
			//response = resource.path("directors").path(id).delete(ClientResponse.class);
			response = resource.path("movies").path(String.valueOf(movie.getId())).put(ClientResponse.class, formData);
			// JSONObject objO = response.getType(MediaType.APPLICATION_JSON);
			System.out.println(response);

		} catch (Exception e) {
			System.out.println(e);
		}
		return response.getStatus();

	}


}
