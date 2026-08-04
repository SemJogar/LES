package app.dto.endereco.request;

import jakarta.validation.constraints.*;

public record PaisRequest(
    String iso,
    String nome
) {}