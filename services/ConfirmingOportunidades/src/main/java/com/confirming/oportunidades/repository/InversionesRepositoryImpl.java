package com.confirming.oportunidades.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.MongoRegexCreator.MatchMode;

import com.confirming.oportunidades.model.Inversion;

public class InversionesRepositoryImpl implements MongoTemplateInvRepository {
	
	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public InversionesRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public List<Inversion> queryInversiones(String username) {
		final Query queryInversiones = new Query();
		final List<Criteria> criteria = new ArrayList<>();
		System.out.println("username : " + username);
		//if(dynamicQuery.getMonto() > 0) {	
			criteria.add(Criteria.where("usernameComprador").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
					username, MatchMode.EXACT), "i"));
		//}

		if(!criteria.isEmpty()) {
			queryInversiones.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
		
		return mongoTemplate.find(queryInversiones, Inversion.class);
    }
}