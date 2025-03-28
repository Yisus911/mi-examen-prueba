package com.examen.prueba.data;

import com.examen.prueba.persistence.document.Telefono;
import com.examen.prueba.presentation.dto.TelefonoRequest;

import java.util.Date;
import java.util.List;

public class DataProvider {

    public static List<Telefono> getListaTelefonos(){
        return List.of(Telefono.builder()
                .id("Abc123defghi456")
                .nombre("Samsung S23 Ultra")
                .marca("Samsung")
                .modelo("Galaxy S23 Ultra")
                .nombreCorto("S23 Ultra")
                .fechaCreacion(new Date())
                .imei(1234567890L)
                .numeroCelular(4422648297L)
                .emailSoporte("jesuspc_905@hotmail.com")
                .isIOS(true)
                .build(),
                Telefono.builder()
                        .id("Abc123defghi456")
                        .nombre("Samsung S24 Ultra")
                        .marca("Samsung")
                        .modelo("Galaxy S24 Ultra")
                        .nombreCorto("S24 Ultra")
                        .fechaCreacion(new Date())
                        .imei(987654321L)
                        .numeroCelular(44226488877L)
                        .emailSoporte("juanpc_905@hotmail.com")
                        .isIOS(true)
                        .build());
    }

    public static Telefono getTelefono(){
        return new Telefono("123abc56789",
                "Samsung S23 Ultra",
                "Samsung",
                "Galaxy S23 Ultra",
                "S23 Ultra",
                new Date(),
                1234567890L,
                4422648297L,
                "jesuspc_905@hotmail.com",
                true);
    }

    public static TelefonoRequest crearNuevoTelefono(){
        return new TelefonoRequest(
                "Samsung S23 Ultra",
                "Samsung",
                "Galaxy S23 Ultra",
                "S23 Ultra",
                new Date(),
                1234567890L,
                4422648297L,
                "jesuspc_905@hotmail.com",
                true);
    }

    public static TelefonoRequest getActualizarTelefono(){
        return new TelefonoRequest(
                "Samsung S23 Ultra",
                "Samsung",
                "Galaxy S23 Ultra",
                "S23 Ultra",
                new Date(),
                4422648297L,
                123456789L,
                "jesuspc_905@hotmail.com",
                false);
    }
}
