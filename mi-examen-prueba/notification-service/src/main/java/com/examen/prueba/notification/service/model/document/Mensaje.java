package com.examen.prueba.notification.service.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "mensajes")
public class Mensaje {
    @Id
    private String id;
    private String descripcion = "Se creó correctamente un nuevo teléfono";
    private Date fechaCreacion = new Date();
}
