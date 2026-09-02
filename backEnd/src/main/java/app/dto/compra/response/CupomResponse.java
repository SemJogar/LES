package app.dto.compra.response;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CupomResponse(
    Integer id,
    String codigo,
    String tpDesconto,
    BigDecimal desconto,
    BigDecimal valorMinimo,
    Integer limiteTotal,
    Integer limiteCliente,
    LocalDate dataInicial,
    LocalDate dataFinal,
    Boolean status
) {}
