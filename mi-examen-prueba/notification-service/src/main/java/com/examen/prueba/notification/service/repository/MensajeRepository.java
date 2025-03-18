package com.examen.prueba.notification.service.repository;

import com.examen.prueba.notification.service.model.document.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends MongoRepository<Mensaje, String> {

}
