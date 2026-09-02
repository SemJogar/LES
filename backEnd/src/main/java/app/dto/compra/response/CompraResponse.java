package app.dto.compra.response;

import java.time.LocalDate;
import java.util.List;

public record CompraResponse(
    Integer id,
    Integer clienteId,
    String sttPagamento,
    String sttTransporte,
    String sttTroca,
    List<CompraLivroResponse> compraLivro,
    LocalDate dtEntrada
) {}