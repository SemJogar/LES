package app.dto.livro.response;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import java.util.List;

public record EstoqueResponse(
    Integer id,
    List<Integer> livroId,
    Integer qt,
    BigDecimal custo,
    String fornecedor,
    LocalDateTime dtEntrada
) {}