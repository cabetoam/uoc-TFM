package com.confirming.oportunidades.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.confirming.oportunidades.model.Inversion;

public interface InversionRepository extends MongoRepository<Inversion, Integer> {

}
