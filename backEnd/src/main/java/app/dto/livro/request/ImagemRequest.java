package app.dto.livro.request;

import jakarta.validation.constraints.*;

public record ImagemRequest(
    // Integer id,
    @NotBlank(message = "Não pode ser null")
    String url
) {}