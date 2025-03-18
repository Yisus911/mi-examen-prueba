package com.examen.prueba.notification.service.listeners;

import com.examen.prueba.notification.service.model.document.Mensaje;
import com.examen.prueba.notification.service.model.document.MensajeActualizado;
import com.examen.prueba.notification.service.repository.MensajeRepository;
import com.examen.prueba.notification.service.service.MensajeService;
import com.examen.prueba.notification.service.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.examen.prueba.notification.service.events.*;

import java.util.Date;

@Component
@Slf4j
public class TelefonoEventListener {

    @Autowired
    private MensajeService service;

    @Autowired
    private MensajeService serviceUpd;

    @KafkaListener(topics = "telefono-topic")
    public void handleTelefonoNotifications(String message){
        var telefonoEvent = JsonUtils.fromJson(message, TelefonoEvent.class);
        System.out.println("Se creó correctamente un nuevo teléfono con el id: "+ telefonoEvent.id());
        Mensaje mensaje = new Mensaje();
        service.guardar(mensaje);
    }

    @KafkaListener(topics = "telefonoUpd-topic")
    public void handleTelefonoUpdNotifications(String message){
        var telefonoEvent = JsonUtils.fromJson(message, TelefonoEvent.class);
        System.out.println("Se actualizó correctamente un teléfono con el id: "+ telefonoEvent.id());
        MensajeActualizado mensaje = new MensajeActualizado();
        service.guardarAct(mensaje);
    }
}
