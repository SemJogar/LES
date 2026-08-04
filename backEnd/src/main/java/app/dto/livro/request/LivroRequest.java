package app.dto.livro.request;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import app.dto.livro.request.CategoriaRequest;
import app.dto.livro.request.DimensaoRequest;
import app.dto.livro.request.PrecificacaoRequest;
import app.dto.livro.request.ImagemRequest;

import java.util.List;

public record LivroRequest(
    // Integer id,
    @NotBlank(message = "Não pode ser null")
    String autor,
    List<Integer> categoriaId,
    Integer ano,
    String titulo,
    String editora,
    String edicao,
    String isbn,
    Integer qtPag,
    String sinopse,
    @Valid
    DimensaoRequest dimensao,
    @Valid
    PrecificacaoRequest precificacao,
    @Valid
    List<ImagemRequest> imagens,
    String codBarras
) {}