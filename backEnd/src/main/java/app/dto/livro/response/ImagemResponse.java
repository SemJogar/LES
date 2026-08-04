package app.dto.livro.response;

import jakarta.validation.constraints.*;

public record ImagemResponse(
    Integer id,
    String url
) {}