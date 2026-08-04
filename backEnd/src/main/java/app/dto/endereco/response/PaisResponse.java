package app.dto.endereco.response;

import jakarta.validation.constraints.*;

public record PaisResponse(
    Integer id,
    String iso,
    String nome
) {}