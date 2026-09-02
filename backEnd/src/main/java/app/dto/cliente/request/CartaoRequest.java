package app.dto.cliente.request;

import jakarta.validation.constraints.*;
import java.util.List;

public record CartaoRequest(
    @NotBlank
    @Size(min = 16, max = 16)
    String numero,
    @NotBlank
    @Size(max = 100)
    String nome,
    @NotBlank
    @Size(min = 4, max = 4)
    String codigo,
    @NotNull
    Integer bandeiraId
) {}