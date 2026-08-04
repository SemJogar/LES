package app.dto.livro.response;

import jakarta.validation.constraints.*;

import java.util.List;

public record PrecificacaoResponse(
    Integer id,
    String grupo,
    String lucro
) {}