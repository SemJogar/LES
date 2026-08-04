package app.dto.cliente.response;

import jakarta.validation.constraints.*;

public record BanderiaResponse(
    Integer id,
    String tipo
) {}