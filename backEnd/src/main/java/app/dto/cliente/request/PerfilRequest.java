package app.dto.cliente.request;

import jakarta.validation.constraints.*;
import java.util.List;

import app.dto.cliente.request.ClienteRequest;

public record PerfilRequest(
    @NotBlank
    @Email
    @Size(max = 150)
    String email,
    @NotBlank
    @Size(max = 255)
    String senha,
    //teoricamente não faz sentido mandar o rank no dto
    // ao ver que ele é algo calculado pelo sistema
    Long rank,
    Integer funcaoId
) {}