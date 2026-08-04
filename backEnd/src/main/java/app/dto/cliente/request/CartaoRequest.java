package app.dto.cliente.request;

import jakarta.validation.constraints.*;
import java.util.List;

import app.dto.cliente.request.BandeiraRequest;
import app.dto.cliente.request.ClienteRequest;

public record CartaoRequest(
    String numero,
    String nome,
    String codigo,
    Integer banderiaId,
    ClienteRequest cliente
) {}