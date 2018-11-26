package com.reviewsys.service;

import java.util.List;

import javax.validation.Valid;

import com.reviewsys.entity.Item;
import com.reviewsys.entity.Review;

public interface ItemService {

	/**
	 * Make a new entry in the database with the supplied item
	 * {@link com.reviewsys.entity.Item}.
	 * 
	 * @param item
	 *            <p>
	 *            The item object.
	 *            </p>
	 * @return The item object after its entry in database.
	 * 
	 */
	Item createItem(Item item);

	/**
	 * 
	 * @return All attributes of all the items {@link com.reviewsys.entity.Item} as
	 *         a list of item objects.
	 * 
	 */
	List<Item> viewAll();

	/**
	 * @param name
	 *            <p>
	 *            the name of the item for which all reviews will be retrieved.
	 *            </p>
	 * @return All reviews {@link com.reviewsys.entity.Review} of the supplied item
	 *         {@link com.reviewsys.entity.Item}.
	 */
	Item viewReviews(String name);

	/**
	 * Update item {@link com.reviewsys.entity.Item} with the supplied review
	 * {@link com.reviewsys.entity.Review} object.
	 * 
	 * @param name
	 *            <p>
	 *            the name of the item for which a new review object is added.
	 *            </p>
	 * @param review
	 *            <p>
	 *            the review object
	 *            </p>
	 * @return The updated item object.
	 */
	void writeReview(String name, Review review);

	/**
	 * 
	 * Edit the review of the supplied item({@link com.reviewsys.entity.Item}).
	 * 
	 * @param name
	 *            <p>
	 *            the name of the item for which comment will be updated.
	 *            </p>
	 * @param review
	 *            <b>See: @link com.reviewsys.entity.Review </b>
	 *            <p>
	 *            new value for comment attribute of Review object.
	 *            </p>
	 * @return The updated item object.
	 */
	Item editReview(String name, @Valid Review review);

	/**
	 * Calculate the average rating for an item {@link com.reviewsys.entity.Item}
	 * (fetched using supplied name).
	 * 
	 * @param name
	 *            <p>
	 *            the name of the item for which comment will be updated.
	 *            </p>
	 * @return The average rating of the supplied item
	 *         ({@link com.reviewsys.entity.Item}) as a string value
	 * 
	 */
	String getAvgRating(String name);
}
