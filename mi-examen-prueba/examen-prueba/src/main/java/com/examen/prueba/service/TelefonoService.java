package com.examen.prueba.service;

import com.examen.prueba.persistence.document.Telefono;
import com.examen.prueba.presentation.dto.TelefonoRequest;
import com.examen.prueba.presentation.dto.TelefonoResponse;
import com.examen.prueba.persistence.repository.TelefonoRepository;
import com.examen.prueba.service.exception.TelefonoNoEncontradoException;
import com.examen.prueba.util.JsonUtils;
import com.examen.prueba.util.mapper.TelefonoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelefonoService {

    private final TelefonoRepository repository;
    private final KafkaTemplate<String, String > kafkaTemplate;
    private static final String TELEFONO_CACHE = "telefonos";

    @Cacheable(value = TELEFONO_CACHE)
    public Page<TelefonoResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(TelefonoMapper.mapper::telefonoToTelefonoResponse);
    }

    @Cacheable(value = TELEFONO_CACHE, key = "#id")
    public TelefonoResponse getTelefonoPorId(String id) throws Exception {
        Telefono registro = repository.findById(id).orElseThrow(() -> new TelefonoNoEncontradoException("Teléfono no encontrado!"));
        return TelefonoMapper.mapper.telefonoToTelefonoResponse(registro);
    }

    @CachePut(value = TELEFONO_CACHE, key = "#result.id()")
    public TelefonoResponse guardar(TelefonoRequest telefonoReq) {
        Telefono telefono = repository.save(TelefonoMapper.mapper.telefonoRequestToTelefono(telefonoReq));
        TelefonoResponse respuesta = TelefonoMapper.mapper.telefonoToTelefonoResponse(telefono);

        //Mensaje para kafka
        this.kafkaTemplate.send("telefono-topic", JsonUtils.toJson(respuesta));
        return respuesta;
    }

    @CacheEvict(value = TELEFONO_CACHE, key = "#id")
    public void eliminar(String id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("Teléfono no encontrado!");
        }
        repository.deleteById(id);
    }

    @CachePut(value = TELEFONO_CACHE, key = "#result.id()")
    public TelefonoResponse actualizar(String id, TelefonoRequest telefonoReq) {
        Telefono registro = repository.save(TelefonoMapper.mapper.telefonoRequestToTelefono(telefonoReq));
        TelefonoResponse reg = TelefonoMapper.mapper.telefonoToTelefonoResponse(registro);
        this.kafkaTemplate.send("telefonoUpd-topic", JsonUtils.toJson(reg));
        return reg;
    }

    @Cacheable(value = TELEFONO_CACHE, key = "#imei")
    public TelefonoResponse getTelefonoByImei(Long imei) throws Exception {
        Thread.sleep(20 * 1000);
        Telefono registro = repository.findByImei(imei).orElseThrow(() -> new TelefonoNoEncontradoException("Teléfono no encontrado!"));
        return TelefonoMapper.mapper.telefonoToTelefonoResponse(registro);
    }

}