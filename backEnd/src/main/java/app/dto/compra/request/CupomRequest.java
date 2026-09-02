package app.dto.compra.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CupomRequest(
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
