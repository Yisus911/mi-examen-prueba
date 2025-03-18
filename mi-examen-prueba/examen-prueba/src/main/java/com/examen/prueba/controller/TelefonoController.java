package com.examen.prueba.controller;

import com.examen.prueba.model.request.TelefonoRequest;
import com.examen.prueba.model.response.TelefonoResponse;
import com.examen.prueba.service.TelefonoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.examen.prueba.utils.SHA256.getSHA;
import static com.examen.prueba.utils.SHA256.toHexString;

@RestController
@RequestMapping("/api/telefonos")
@RequiredArgsConstructor
public class TelefonoController {

    private final TelefonoService service;
    private static final String CLAVE = "MIEXAMENPRUEBA";

    @GetMapping("/getTodos")
    @ResponseStatus(HttpStatus.OK)
    public Page<TelefonoResponse> getTodos(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return this.service.getAll(pageable);
    }

    @PostMapping("/guardar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TelefonoResponse> guardar(@RequestBody @Validated TelefonoRequest telefono) {
        return ResponseEntity.ok(service.guardar(telefono));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) throws Exception {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TelefonoResponse> actualizar(@PathVariable String id,
                                                       @RequestBody TelefonoRequest telefono) throws Exception {
        TelefonoResponse response = service.actualizar(id, telefono);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getTelefonoPorImei/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public TelefonoResponse getTelefonoPorImei(@PathVariable Long imei) throws Exception {
        return this.service.getTelefonoByImei(imei);
    }

    @GetMapping("/getTelefonoPorId/{id}")
    public ResponseEntity<TelefonoResponse> getTelefonoPorId(@RequestHeader("DATA") String data,
                                                             @PathVariable String id) throws Exception {
        if(toHexString(getSHA(CLAVE)).equals(data)){
            return new ResponseEntity<>(service.getTelefonoPorId(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,
                    HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}