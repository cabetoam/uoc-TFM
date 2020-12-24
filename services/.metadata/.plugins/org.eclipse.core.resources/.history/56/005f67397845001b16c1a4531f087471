package com.confirming.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.MongoRegexCreator.MatchMode;
import org.springframework.data.mongodb.core.query.Query;

import com.confirming.api.model.User;
import com.confirming.api.model.UserInversor;
import com.confirming.api.model.UserOfertante;

public class UserRepositoryImpl implements MongoTemplateRepository{

	private final MongoTemplate mongoTemplate;
	 
	@Autowired
	public UserRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
 		
		@Override
		public List<User> query(DynamicQuery dynamicQuery) {
			final Query query = new Query();
			final List<Criteria> criteria = new ArrayList<>();
			System.out.println(" login : " + dynamicQuery.getLogin());
			if(dynamicQuery.getLogin() != null) {	
				criteria.add(Criteria.where("login").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
						dynamicQuery.getLogin(), MatchMode.EXACT), "i"));
				criteria.add(Criteria.where("contrasena").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
						dynamicQuery.getContrasena(), MatchMode.EXACT), "i"));					
			}

		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
		
		return mongoTemplate.find(query, User.class);

	
		 /*	@Override
		public List<User> query(DynamicQuery dynamicQuery) {
			final Query query = new Query();
			final List<Criteria> criteria = new ArrayList<>();
			System.out.println("name" + dynamicQuery.getStrname());
			if(dynamicQuery.getStrname() != null) {	
				criteria.add(Criteria.where("name").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
						dynamicQuery.getStrname(), MatchMode.CONTAINING
				), "i"));
				criteria.add(Criteria.where("lastname").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
						dynamicQuery.getStrlastname(), MatchMode.CONTAINING
				), "i"));			
			}
			
	 		if(dynamicQuery.getStrname() != null) {
				criteria.add(Criteria.where("name").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
						dynamicQuery.getStrname(), MatchMode.CONTAINING
				), "i"));
			}

	 		if(dynamicQuery.getPublishDateBefore() != null) {
				criteria.add(Criteria.where("publishDate").lte(dynamicQuery.getPublishDateBefore()));
			}
			if(dynamicQuery.getPublishDateAfter() != null) {
				criteria.add(Criteria.where("publishDate").gte(dynamicQuery.getPublishDateAfter()));
			}
			if(dynamicQuery.getSubject() != null) {
				criteria.add(Criteria.where("subjects").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
						dynamicQuery.getSubject(), Part.Type.SIMPLE_PROPERTY
				), "i"));
			}*/
	}
		
		
		
	@Override
	public List<UserInversor> queryNombreInversor(DynamicQuery dynamicQuery) {
		final Query queryNombre = new Query();
		final List<Criteria> criteria = new ArrayList<>();
		System.out.println(" login : " + dynamicQuery.getLogin());
		if(dynamicQuery.getLogin() != null) {	
			criteria.add(Criteria.where("email").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
					dynamicQuery.getLogin(), MatchMode.EXACT), "i"));
		}

		if(!criteria.isEmpty()) {
			queryNombre.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
		
		return mongoTemplate.find(queryNombre, UserInversor.class);
    }	
	
	@Override
	public List<UserOfertante> queryNombreOfertante(DynamicQuery dynamicQuery) {
		final Query queryNombre = new Query();
		final List<Criteria> criteria = new ArrayList<>();
		System.out.println(" login : " + dynamicQuery.getLogin());
		if(dynamicQuery.getLogin() != null) {	
			criteria.add(Criteria.where("email").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
					dynamicQuery.getLogin(), MatchMode.EXACT), "i"));
		}

		if(!criteria.isEmpty()) {
			queryNombre.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
		
		return mongoTemplate.find(queryNombre, UserOfertante.class);
    }	
		
}
