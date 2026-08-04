package app.dto.livro.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import java.util.List;

public record EstoqueRequest(
    // Integer id,
    // @NotBlank(message = "Não pode ser null")
    List<Integer> livroId,
    Integer qt,
    BigDecimal custo,
    String fornecedor,
    LocalDateTime dtEntrada
) {}