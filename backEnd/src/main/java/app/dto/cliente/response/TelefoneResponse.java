package app.dto.cliente.response;

import jakarta.validation.constraints.*;

import app.dto.cliente.response.ClienteResponse;

public record TelefoneResponse(
    Integer id,
    String tp,
    String ddd,
    String numero,
    ClienteResponse cliente
) {}
