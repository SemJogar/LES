package app.dto.livro.response;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DimensaoResponse(
    Integer id,
    @NotBlank(message = "Não pode ser null")
    BigDecimal altura,
    BigDecimal largura,
    BigDecimal peso,
    BigDecimal profundidade
) {}