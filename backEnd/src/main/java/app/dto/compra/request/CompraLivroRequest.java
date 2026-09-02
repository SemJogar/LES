package app.dto.compra.request;

import java.math.BigDecimal;

// import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CompraLivroRequest(
    Integer compraId,
    Integer livroId,
    Short quantidade,
    BigDecimal valor
) {}
