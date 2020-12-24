package com.confirming.reg.inversor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.confirming.reg.inversor.model.Inversor;

public interface InversorRepository extends MongoRepository<Inversor, Integer>, InversorRepositoryCustom {

}
