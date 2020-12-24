package com.confirming.reg.inversor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.confirming.reg.inversor.model.Login;

public interface LoginRepository extends MongoRepository<Login, Integer> {

}
