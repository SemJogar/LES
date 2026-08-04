package app.dto.endereco.request;

import jakarta.validation.constraints.*;

import app.dto.endereco.request.PaisRequest;

public record EstadoRequest(
    String iso,
    String nome,
    PaisRequest pais
) {}