package com.confirming.oportunidades.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.confirming.oportunidades.model.Archivo;

public interface ArchivoRepository extends MongoRepository<Archivo, Integer> {

}
