package app.dto.cliente.request;

import jakarta.validation.constraints.*;

import app.dto.cliente.request.ClienteRequest;

public record TelefoneRequest(
    @NotBlank
    @Size(min = 2, max = 2)
    String tp,
    @NotBlank
    @Size(min = 2, max = 2)
    String ddd,
    @NotBlank
    @Size(min = 9, max = 9)
    String numero
) {}
