package com.examen.prueba.presentation.dto;

import lombok.Builder;

@Builder
public record ExceptionDTO (int code, String message) {
}
