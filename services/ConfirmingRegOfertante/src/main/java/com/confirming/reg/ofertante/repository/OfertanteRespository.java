package com.confirming.reg.ofertante.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.confirming.reg.ofertante.model.Ofertante;

public interface OfertanteRespository extends MongoRepository<Ofertante, Integer> {


}
