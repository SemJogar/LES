package app.dto.livro.request;

import jakarta.validation.constraints.*;

import java.util.List;

public record PrecificacaoRequest(
    // Integer id,
    @NotBlank(message = "Não pode ser null")
    String grupo,
    String lucro
) {}