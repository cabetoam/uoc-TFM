package com.confirming.reg.inversor.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;

import com.confirming.reg.inversor.model.Login;

import org.springframework.data.mongodb.core.query.MongoRegexCreator.MatchMode;


public class InversorRepositoryImpl implements InversorRepositoryCustom {
	
	private final MongoTemplate mongoTemplate;
	 
	@Autowired
	public InversorRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public boolean query(DynamicQueryLogin dynamicQueryLogin) {
		final Query query = new Query();
		final List<Criteria> criteria = new ArrayList<>();
		List<Login> login = new ArrayList<Login>();
		boolean loginExist = false;
		System.out.println(" login : " + dynamicQueryLogin.getLogin());
		if(dynamicQueryLogin.getLogin() != null) {	
			criteria.add(Criteria.where("login").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
					dynamicQueryLogin.getLogin(), MatchMode.CONTAINING
			), "i"));						
		}
		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
	
		//mongoTemplate.find(query, DataQueryLogin.class);
		login = mongoTemplate.find(query, Login.class);
		try
		{
			if (login.get(0).getLogin().equals(dynamicQueryLogin.getLogin()))
				loginExist = true;
			else
				loginExist = false;
		}
		catch (Exception e){
			loginExist = false;
		}		
		return loginExist;
	}
 
}
