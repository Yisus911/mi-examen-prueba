package com.examen.prueba.model.response;

import com.examen.prueba.model.document.Telefono;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
public record TelefonoResponse(String id, String nombre, String marca,
                               String modelo, String nombreCorto,
                               Date fechaCreacion,
                               Long imei,
                               long numeroCelular,
                               String emailSoporte,
                               boolean isIOS
)  implements Serializable {

    public static TelefonoResponse mapeo(Telefono tel){
        return new TelefonoResponse(tel.getId(), tel.getNombre(), tel.getMarca(),
                tel.getModelo(), tel.getNombreCorto(), tel.getFechaCreacion(),
                tel.getImei(), tel.getNumeroCelular(), tel.getEmailSoporte(),
                tel.getIsIOS()
                );
    }

}