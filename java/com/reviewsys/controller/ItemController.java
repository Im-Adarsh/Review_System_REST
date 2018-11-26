package com.reviewsys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reviewsys.entity.Item;
import com.reviewsys.entity.Review;
import com.reviewsys.service.ItemService;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController {

	@Autowired
	ItemService itemService;

	/**
	 * The object of class Item ({@link com.reviewsys.entity.Item}) comes in JSON
	 * request. For a valid request, store into the database.
	 * 
	 */
	@RequestMapping(value = "/create_item", method = RequestMethod.POST)
	public Item createItem(@Valid @RequestBody Item item) {
		return itemService.createItem(item);
	}

	/**
	 * For this URL, return all the items to which a review comment has been
	 * provided.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Item> getAll(HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		return itemService.viewAll();
	}

	/**
	 * For this GET request, fetch all the reviews for item an item (identified by
	 * name in URL).
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public Item viewReveiwsForThisItem(@PathVariable("name") String name, HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		return itemService.viewReviews(name);
	}

	/**
	 * Get a Review object ({@link package com.reviewsys.entity.Review}) from JSON
	 * and insert to the database.
	 */
	@RequestMapping(value = "/{name}/write_review", method = RequestMethod.POST)
	public void addReviewForThisItem(@PathVariable("name") String name, @Valid @RequestBody Review review,
			HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		itemService.writeReview(name, review);
	}

	/** Edit item review. */
	@RequestMapping(value = "/{name}/edit_item_review", method = RequestMethod.POST)
	public Item editReviewForThisItem(@PathVariable("name") String name, @Valid @RequestBody Review review,
			HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		return itemService.editReview(name, review);
	}

	/** View the average rating if an item. */
	@RequestMapping(value = "/{name}/view_item_average_rating", method = RequestMethod.GET)
	public String getAvgRatingForThisItem(@PathVariable("name") String name, HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		return itemService.getAvgRating(name);
	}
}
