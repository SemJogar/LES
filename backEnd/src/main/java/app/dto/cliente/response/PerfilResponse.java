package app.dto.cliente.response;

import jakarta.validation.constraints.*;
import java.util.List;

import app.dto.cliente.request.ClienteRequest;

public record PerfilResponse(
    Integer id,
    String email,
    String senha,
    Long rank,
    FuncaoResponse funcao
) {}