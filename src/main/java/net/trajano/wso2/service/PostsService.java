package net.trajano.wso2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.annotations.Api;

@Api
@Path("/posts")
@Component
@CrossOrigin(origins = "*")
public class PostsService {
	private Map<Integer, Post> posts = new TreeMap<>();

	@Autowired
	private SubService subservice;

	@OPTIONS
	@Path("{path : .*}")
	public Response options() {
		// return Response.ok("").header("Access-Control-Allow-Origin", "*")
		// .header("Access-Control-Allow-Headers", "origin, content-type,
		// accept, authorization")
		// .header("Access-Control-Allow-Credentials", "true")
		// .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE,
		// OPTIONS, HEAD")
		// .header("Access-Control-Max-Age", "1209600").build();
		return Response.ok().build();
	}

	@PostConstruct
	public void init() {
		posts.put(1, new Post(1, "Title 1", "long blah body 1"));
		posts.put(2, new Post(2, "Short Title", "Short Story"));
		posts.put(3, new Post(3, "React Title", "Short Story"));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPosts() {
		List<Post> ret = new ArrayList<Post>();
		posts.values().forEach((Post p) -> ret.add(p));
		return Response.ok(posts).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getOne(@PathParam("id") int id) {
		return Response.ok(posts.get(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Post p) {
		Post np = new Post();
		np.setTitle(p.getTitle());
		np.setBody(p.getBody());
		np.setId(new Random().nextInt());
		posts.put(np.getId(), np);
		return Response.created(UriBuilder.fromResource(getClass()).fragment(String.valueOf(np.getId())).build())
				.build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response update(@PathParam("id") int id, Post p) {
		posts.put(p.getId(), p);
		return Response.ok(p).build();
	}

	@GET
	@Path("/subs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostsFromSub() {
		return Response.ok(subservice.getPosts()).build();
	}
}
