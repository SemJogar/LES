package app.dto.compra;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CompraResponse(
    Integer id,
    String sttPagamento,
    String sttTransporte,
    String sttTroca,
    LocalDate dtEntrada
) {}
