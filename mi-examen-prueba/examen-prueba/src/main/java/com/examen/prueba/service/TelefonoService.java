package com.examen.prueba.service;

import com.examen.prueba.model.document.Telefono;
import com.examen.prueba.model.request.TelefonoRequest;
import com.examen.prueba.model.response.TelefonoResponse;
import com.examen.prueba.repository.TelefonoRepository;
import com.examen.prueba.utils.JsonUtils;
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
        return repository.findAll(pageable)
                .map(TelefonoResponse::mapeo);
    }

    @Cacheable(value = TELEFONO_CACHE, key = "#id")
    public TelefonoResponse getTelefonoPorId(String id) throws Exception {
        Telefono registro = repository.findById(id).orElseThrow(() -> new Exception("Teléfono no encontrado!"));
        return TelefonoResponse.mapeo(registro);
    }

    @CachePut(value = TELEFONO_CACHE, key = "#result.id()")
    public TelefonoResponse guardar(TelefonoRequest telefonoReq) {
        Telefono telefono = TelefonoRequest.mapeo(telefonoReq);
        Telefono registro = repository.save(telefono);
        TelefonoResponse respuesta = TelefonoResponse.mapeo(registro);

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
        Telefono telefono = Telefono.builder()
                .id(id).nombre(telefonoReq.getNombre())
                .marca(telefonoReq.getMarca())
                .modelo(telefonoReq.getModelo())
                .nombreCorto(telefonoReq.getNombreCorto())
                .fechaCreacion(telefonoReq.getFechaCreacion())
                .imei(telefonoReq.getImei())
                .numeroCelular(telefonoReq.getNumeroCelular())
                .emailSoporte(telefonoReq.getEmailSoporte())
                .isIOS(telefonoReq.getIsIOS())
                .build();
        Telefono registro = repository.save(telefono);
        TelefonoResponse reg = TelefonoResponse.mapeo(registro);
        this.kafkaTemplate.send("telefonoUpd-topic", JsonUtils.toJson(reg));
        return reg;
    }

    @Cacheable(value = TELEFONO_CACHE, key = "#imei")
    public TelefonoResponse getTelefonoByImei(Long imei) throws Exception {
        Thread.sleep(20 * 1000);
        Telefono registro = repository.findByImei(imei).orElseThrow(() -> new Exception("Teléfono no encontrado!"));
        return TelefonoResponse.mapeo(registro);
    }

}