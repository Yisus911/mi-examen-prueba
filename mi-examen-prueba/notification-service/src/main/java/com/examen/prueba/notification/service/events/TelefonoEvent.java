package com.examen.prueba.notification.service.events;

import java.io.Serializable;
import java.util.Date;

public record TelefonoEvent(String id, String nombre, String marca,
                            String modelo, String nombreCorto,
                            Date fechaCreacion,
                            Long imei,
                            long numeroCelular,
                            String emailSoporte,
                            boolean isIOS) implements Serializable {

}