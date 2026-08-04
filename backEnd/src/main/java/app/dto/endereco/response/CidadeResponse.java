package app.dto.endereco.response;

import jakarta.validation.constraints.*;

import app.dto.endereco.response.EstadoResponse;

public record CidadeResponse(
    Integer id,
    String nome,
    EstadoResponse estado
) {}