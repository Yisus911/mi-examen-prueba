package com.examen.prueba.presentation.dto;

import java.io.Serializable;
import java.util.Date;

public record TelefonoResponse(String id, String nombre, String marca,
                               String modelo, String nombreCorto,
                               Date fechaCreacion,
                               Long imei,
                               long numeroCelular,
                               String emailSoporte,
                               boolean isIOS
)  implements Serializable {

}