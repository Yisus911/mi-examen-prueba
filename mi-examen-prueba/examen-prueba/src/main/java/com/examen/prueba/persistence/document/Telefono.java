package com.examen.prueba.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "telefono")
@Validated
public class Telefono implements Serializable {
    @Id
    private String id;
    private String nombre;
    private String marca;
    private String modelo;
    private String nombreCorto;
    private Date fechaCreacion;
    @Indexed(unique = true)
    private Long imei;
    private long numeroCelular;
    private String emailSoporte;
    private Boolean isIOS;

}
