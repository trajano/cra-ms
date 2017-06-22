package net.trajano.wso2.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;

@Component
@Api
@Path("/subposts")
public class SubService {
	private List<Post> posts = new ArrayList<>();

	@PostConstruct
	public void init() {
		posts.add(new Post(1, "Title 1", "long blah body 1 from sub"));
		posts.add(new Post(2, "Short Title", "Short Story from sub"));
	}

	public List<Post> getPosts() {
		return posts;
	}
}
