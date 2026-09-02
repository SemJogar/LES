package app.dto.cliente.response;

import jakarta.validation.constraints.*;

public record BandeiraResponse(
    Integer id,
    String tipo
) {}