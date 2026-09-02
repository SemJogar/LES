package app.dto.compra.request;

import java.time.LocalDate;
import java.util.List;

public record CompraRequest(
    Integer clienteId, // Faltava o ID do cliente da FK com_cli_id
    String sttPagamento,
    String sttTransporte,
    String sttTroca,
    List<CompraLivroRequest> comprasLivros, // Deve ser lista para a tabela compras_livros
    List<Integer> cuponsIds, // IDs dos cupons usados (opcional, para a tabela cupom_usos)
    LocalDate dtEntrada
) {}