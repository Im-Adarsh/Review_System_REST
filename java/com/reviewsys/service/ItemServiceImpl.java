package com.reviewsys.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewsys.dao.ItemDAO;
import com.reviewsys.entity.Item;
import com.reviewsys.entity.Review;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemDAO itemDao;

	@Override
	public Item createItem(Item item) {
		return itemDao.createItem(item);
	}

	@Override
	public List<Item> viewAll() {
		return itemDao.viewAll();
	}

	@Override
	public Item viewReviews(String name) {
		return itemDao.viewReviews(name);
	}

	@Override
	public void writeReview(String name, Review review) {
		itemDao.writeReview(name, review);
	}

	@Override
	public String getAvgRating(String name) {
		return String.valueOf(itemDao.getAvgRating(name));
	}

	@Override
	public Item editReview(String name, @Valid Review review) {
		itemDao.editComment(name, review.getComment(), review.getAuthor());
		if (review.getRating() != 0)
			itemDao.editRating(name, review.getRating(), review.getAuthor());
		return itemDao.viewReviews(name);
	}
}
