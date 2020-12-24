package com.confirming.oportunidades.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.confirming.oportunidades.model.Factura;


public interface FacturaRepository extends MongoRepository<Factura, Integer> {
	

}
