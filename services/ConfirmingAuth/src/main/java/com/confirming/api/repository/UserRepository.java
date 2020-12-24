package com.confirming.api.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.confirming.api.model.User;

public interface UserRepository extends MongoRepository<User, Integer>, MongoTemplateRepository {
	
	//List<User> findByTitleContainingOrderByTitle(String titleContains);

}
    