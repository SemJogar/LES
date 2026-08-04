package app.dto.cliente.response;

import jakarta.validation.constraints.*;
import java.util.List;

import app.dto.cliente.response.BanderiaResponse;
import app.dto.cliente.response.ClienteResponse;

public record CartaoResponse(
    Integer id,
    String numero,
    String nome,
    String codigo,
    Integer banderiaId,
    ClienteResponse cliente
) {}