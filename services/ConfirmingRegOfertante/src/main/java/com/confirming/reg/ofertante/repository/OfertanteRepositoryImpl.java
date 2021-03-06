package com.confirming.reg.ofertante.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;

import com.confirming.reg.ofertante.model.Ofertante;

import org.springframework.data.mongodb.core.query.MongoRegexCreator.MatchMode;


public class OfertanteRepositoryImpl implements MongoTemplateRepository{
	
	private final MongoTemplate mongoTemplate;
	 
	@Autowired
	public OfertanteRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
 		
	@Override
	public List<Ofertante> query(String username) {
		final Query query = new Query();
		final List<Criteria> criteria = new ArrayList<>();
		System.out.println(" username : " + username);
		if(username != null) {	
			criteria.add(Criteria.where("email").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
					username, MatchMode.EXACT), "i"));		
		}

		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
	
		return mongoTemplate.find(query, Ofertante.class);
    }
}
