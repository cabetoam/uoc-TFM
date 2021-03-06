package com.confirming.oportunidades.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.confirming.oportunidades.model.Inversion;
import com.confirming.oportunidades.model.Oportunidad;

public interface OportunidadesRepository extends MongoRepository<Oportunidad, Integer>, MongoTemplateRepository {

}
