package com.confirming.reg.ofertante.repository;

import java.util.List;

import com.confirming.reg.ofertante.model.Ofertante;


public interface MongoTemplateRepository {
	
	List<Ofertante> query(String username); 

}
