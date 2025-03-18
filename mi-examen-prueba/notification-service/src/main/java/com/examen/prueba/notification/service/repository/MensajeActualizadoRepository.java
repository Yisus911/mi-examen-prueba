package com.examen.prueba.notification.service.repository;

import com.examen.prueba.notification.service.model.document.Mensaje;
import com.examen.prueba.notification.service.model.document.MensajeActualizado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeActualizadoRepository extends MongoRepository<MensajeActualizado, String> {

}
