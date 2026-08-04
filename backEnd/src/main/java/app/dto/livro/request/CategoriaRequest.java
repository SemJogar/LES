package app.dto.livro.request;

import jakarta.validation.constraints.*;

public record CategoriaRequest(
    // Integer id,
    @NotBlank(message = "Não pode ser null")
    String nome
) {}