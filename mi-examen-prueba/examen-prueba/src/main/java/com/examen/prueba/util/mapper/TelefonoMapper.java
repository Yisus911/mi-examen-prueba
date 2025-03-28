package com.examen.prueba.util.mapper;

import com.examen.prueba.persistence.document.Telefono;
import com.examen.prueba.presentation.dto.TelefonoRequest;
import com.examen.prueba.presentation.dto.TelefonoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TelefonoMapper {

    TelefonoMapper mapper = Mappers.getMapper(TelefonoMapper.class);

    TelefonoResponse telefonoToTelefonoResponse(Telefono telefono);
    Telefono telefonoRequestToTelefono(TelefonoRequest telefonoRequest);

}
