package com.reviewsys.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <h1>Items POJO</h1> Contains attributes:</br>
 * {@code String _id} </br>
 * {@code String name} </br>
 * {@code String List<Review)> review}( {@link com.reviewsys.entity.Review} )
 * 
 */
@Document(collection = "items")
public class Item {

	@Id
	private ObjectId _id;
	@Field("item")
	private String name;
	private List<Review> review;

	public Item() {

	}

	public Item(ObjectId _id, String name, List<Review> review) {
		super();
		this._id = _id;
		this.name = name;
		this.review = review;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}
}
