package net.trajano.wso2.service;

public class Post {
	private int id;
	private String title;
	private String body;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Post() {

	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Post(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.userId = 1;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public Integer getId() {
		return id;
	}
}
