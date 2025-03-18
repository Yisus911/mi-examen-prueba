package com.examen.prueba.repository;

import com.examen.prueba.model.document.Telefono;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelefonoRepository extends MongoRepository<Telefono, String> {

    Page<Telefono> findAll(Pageable pageable);
    Optional<Telefono> findByImei(Long imei);
}