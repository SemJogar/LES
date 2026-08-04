package app.dto.livro.response;

import jakarta.validation.constraints.*;

public record CategoriaResponse(
    Integer id,
    String nome
) {}