package com.examen.prueba.presentation.dto;

import com.examen.prueba.persistence.document.Telefono;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoRequest implements Serializable {
    @Valid

    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{2,24}$", message = "El valor del campo [nombre] no debe contener carácteres especiales.")
    private String nombre;
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{2,24}$", message = "El valor del campo [marca] no debe contener carácteres especiales.")
    private String marca;
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9()]{2,32}$", message = "El valor del campo [modelo] no debe contener carácteres especiales.")
    private String modelo;
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{2,24}$", message = "El valor del campo [nombreCorto] no debe contener carácteres especiales.")
    private String nombreCorto;
    @NotNull
    private Date fechaCreacion;
    @NotNull
    private Long imei;
    @Nullable
    private long numeroCelular;
    @Email
    private String emailSoporte;
    private Boolean isIOS;

}