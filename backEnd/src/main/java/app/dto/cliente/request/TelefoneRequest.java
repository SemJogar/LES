package app.dto.cliente.request;

import jakarta.validation.constraints.*;

import app.dto.cliente.request.ClienteRequest;

public record TelefoneRequest(
    String tp,
    String ddd,
    String numero,
    ClienteRequest cliente
) {}
