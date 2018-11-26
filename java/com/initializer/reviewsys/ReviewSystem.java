package com.initializer.reviewsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.reviewsys.repository.ItemRepository;

/**
 * This is an example of exposing REST API. The "ReviewSystem" takes an item and
 * collects its reviews. MongoDB is used for storage of data.
 */
@SpringBootApplication(scanBasePackages = "com.reviewsys")
@EnableMongoRepositories(basePackageClasses = ItemRepository.class)
public class ReviewSystem {

	public static void main(String[] args) {
		SpringApplication.run(ReviewSystem.class, args);
	}
}
