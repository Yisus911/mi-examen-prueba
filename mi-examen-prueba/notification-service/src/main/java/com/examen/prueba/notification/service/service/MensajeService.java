package com.examen.prueba.notification.service.service;

import com.examen.prueba.notification.service.model.document.Mensaje;
import com.examen.prueba.notification.service.model.document.MensajeActualizado;
import com.examen.prueba.notification.service.repository.MensajeActualizadoRepository;
import com.examen.prueba.notification.service.repository.MensajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository repository;

    @Autowired
    private MensajeActualizadoRepository repositoryAct;

    public void guardar(Mensaje mensaje) {
        repository.save(mensaje);
    }

    public void guardarAct(MensajeActualizado mensaje) {
        repositoryAct.save(mensaje);
    }
}
