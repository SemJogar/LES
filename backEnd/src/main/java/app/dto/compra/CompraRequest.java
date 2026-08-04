package app.dto.compra;

// import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CompraRequest(
    String sttPagamento,
    String sttTransporte,
    String sttTroca,
    LocalDate dtEntrada
) {}
