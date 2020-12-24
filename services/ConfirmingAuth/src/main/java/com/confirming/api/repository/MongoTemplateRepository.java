package com.confirming.api.repository;

import java.util.List;

import com.confirming.api.model.User;
import com.confirming.api.model.UserInversor;
import com.confirming.api.model.UserOfertante;

	public interface MongoTemplateRepository {
	
		List<User> query(DynamicQuery dynamicQuery); 
		
		List<UserInversor> queryNombreInversor(DynamicQuery dynamicQuery); 
		
		List<UserOfertante> queryNombreOfertante(DynamicQuery dynamicQuery);
}
