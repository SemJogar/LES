package app.dto.endereco.response;

import jakarta.validation.constraints.*;

import app.dto.endereco.response.PaisResponse;

public record EstadoResponse(
    Integer id,
    String iso,
    String nome,
    PaisResponse pais
) {}