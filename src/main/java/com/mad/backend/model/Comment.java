package com.mad.backend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
public class Comment {
	@Id
	private String id;
	private String title;
	private String content;
	private int ratings;
	//@DBRef
	//private User user;
	@DBRef
	private Museum museum;
	private LocalDateTime date;

	public Comment() {
	}

	// public Comment(String title, String content, int ratings, User user, Museum museum) {
	// 	super();
	// 	this.title = title;
	// 	this.content = content;
	// 	this.ratings = ratings;
	// 	this.user = user;
	// 	this.museum = museum;
	// }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	// public User getUser() {
	// 	return user;
	// }

	// public void setUser(User user) {
	// 	this.user = user;
	// }

	public Museum getMuseum() {
		return museum;
	}

	public void setMuseum(Museum museum) {
		this.museum = museum;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	// ------- might need to make more changes here
	// @Override
	// public String toString() {
	// 	return "Comment [id=" + id + ", title=" + title + ", content=" + content + ", ratings=" + ratings +
	// 			", user=" + user +
	// 			", museum=" + museum +
	// 			", date=" + date + "]";
	// }

}