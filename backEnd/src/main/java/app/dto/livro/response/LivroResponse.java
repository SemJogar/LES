package app.dto.livro.response;

import app.dto.livro.response.CategoriaResponse;
import app.dto.livro.response.DimensaoResponse;
import app.dto.livro.response.PrecificacaoResponse;
import app.dto.livro.response.ImagemResponse;

import java.util.List;

public record LivroResponse(
    Integer id,
    String autor,
    List<CategoriaResponse> categoria,
    Integer ano,
    String titulo,
    String editora,
    String edicao,
    String isbn,
    Integer qtPag,
    String sinopse,
    DimensaoResponse dimensao,
    PrecificacaoResponse precificacao,
    List<ImagemResponse> imagem,
    String codBarras
) {}

