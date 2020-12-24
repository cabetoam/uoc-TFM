package com.confirming.oportunidades.repository;

import java.util.List;
import com.confirming.oportunidades.model.Oportunidad;

public interface MongoTemplateRepository {
	
	List<Oportunidad> queryOportunidades(DynamicQuery dynamicQuery); 
	
}
