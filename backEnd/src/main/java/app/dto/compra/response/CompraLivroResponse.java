package app.dto.compra.response;

import java.math.BigDecimal;

public record CompraLivroResponse(
    Integer id,
    Integer compraId,
    Integer livroId,
    Short quantidade,
    BigDecimal valor
) {}