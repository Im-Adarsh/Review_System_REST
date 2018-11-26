package com.reviewsys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.reviewsys.entity.Item;
import com.reviewsys.entity.Review;
import com.reviewsys.repository.ItemRepository;

@Repository
public class ItemDAOImpl implements ItemDAO {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public List<Item> viewAll() {
		return itemRepository.findAll();
	}

	@Override
	public List<Item> viewAllItems() {
		return viewAll();
	}

	@Override
	public Item viewReviews(String name) {
		return itemRepository.findByName(name);
	}

	@Override
	public void writeReview(String name, Review review) {
		Criteria criteria = Criteria.where("name").is(name);
		Query query = new Query(criteria);

		mongoTemplate.updateFirst(query, new Update().push("review", review), Item.class);
	}

	@Override
	public void editRating(String name, int newRating, String author) {
		Criteria criteria_item = Criteria.where("name").is(name);
		Criteria criteria_review = Criteria.where("review").elemMatch(Criteria.where("author").is(author));

		Query query = new Query(criteria_item.andOperator(criteria_review));
		mongoTemplate.updateFirst(query, new Update().set("review.$.rating", newRating), Item.class);
	}

	@Override
	public void editComment(String name, String comment, String author) {
		Criteria criteria_item = Criteria.where("name").is(name);
		Criteria criteria_review = Criteria.where("review").elemMatch(Criteria.where("author").is(author));

		Query query = new Query(criteria_item.andOperator(criteria_review));
		mongoTemplate.updateFirst(query, new Update().set("review.$.comment", comment), Item.class);
	}

	@Override
	public double getAvgRating(String name) {
		/*
		 * ---Working db query--- db.items.aggregate([ {$match: {item:"pen"}},{$unwind:
		 * "$review"}, {$group: {_id: "$_id", "avgRating": {$avg: "$review.rating"}}}])
		 * ----Query using Spring not giving result----- Aggregation aggregation =
		 * Aggregation.newAggregation(newAggregation.match(Criteria.where("name").is(
		 * name)), Aggregation.unwind("review") ,
		 * Aggregation.group("_id").avg("review.rating")); return
		 * mongoTemplate.aggregate(aggregation, Item.class, Double.class);
		 */

		List<Review> reviews = itemRepository.findByName(name).getReview();
		return reviews.parallelStream().mapToInt(Review::getRating).average().getAsDouble();
	}

}
