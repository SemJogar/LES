package app.dto.endereco.request;

import jakarta.validation.constraints.*;

import app.dto.endereco.request.EstadoRequest;

public record CidadeRequest(
    String nome,
    EstadoRequest estado
) {}