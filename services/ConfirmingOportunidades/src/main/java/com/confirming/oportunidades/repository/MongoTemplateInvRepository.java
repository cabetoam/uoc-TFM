package com.confirming.oportunidades.repository;

import java.util.List;

import com.confirming.oportunidades.model.Inversion;

public interface MongoTemplateInvRepository {
	
	List<Inversion> queryInversiones(String username);

}
