package app.dto.livro.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DimensaoRequest(
    // Integer id,
    // @NotBlank(message = "Não pode ser null")
    BigDecimal altura,
    BigDecimal largura,
    BigDecimal peso,
    BigDecimal profundidade
) {}