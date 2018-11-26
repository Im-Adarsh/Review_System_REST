package com.reviewsys.dao;

import java.util.List;

import com.reviewsys.entity.Item;
import com.reviewsys.entity.Review;

/**
 * The java-docs for these methods are similar to the ones provided at
 * {@link com.reviewsys.service.ItemService} and
 * {@link com.reviewsys.controller.ItemController}
 */
public interface ItemDAO {
	Item createItem(Item item);

	List<Item> viewAll();

	Item viewReviews(String name);

	void writeReview(String name, Review review);

	void editRating(String name, int newRating, String author);

	double getAvgRating(String name);

	List<Item> viewAllItems();

	void editComment(String name, String comment, String author);
}
