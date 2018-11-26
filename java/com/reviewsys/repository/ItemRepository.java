package com.reviewsys.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.reviewsys.entity.Item;

public interface ItemRepository extends MongoRepository<Item,String>{

	Item findByName(String name);
}
