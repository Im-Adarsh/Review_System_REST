package com.reviewsys.entity;

/**
 * <h1>Review POJO</h1> Contains attributes:</br>
 * {@code String comment} </br>
 * {@code int rating} </br>
 * {@code String author}
 * 
 */
public class Review {

	private String comment;
	private int rating;
	private String author;

	public Review() {

	}

	public Review(String comment, String author, int rating) {
		super();
		this.comment = comment;
		this.author = author;
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
