package app.mapper;

import app.dto.livro.request.LivroRequest;
import app.dto.livro.response.LivroResponse;
import app.model.livro.Livro;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE // Ignora campos nulos no PUT
)
public interface LivroMapper {

    // 1. DTO -> Entidade (Criação)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true) // Tratado no Service via Repositório/IDs
    Livro requestToLivro(LivroRequest dto);

    // 2. Entidade -> DTO (Resposta)
    @Mapping(target = "categoria", source = "categorias")
    @Mapping(target = "imagem", source = "imagens")
    LivroResponse livroToResponse(Livro livro);

    // 3. Atualizar Entidade Existente (PUT)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    // @Mapping(target = "imagens", ignore = true) // Imagens tratadas no AfterMapping para evitar quebra do Cascade
    void atualizarLivro(LivroRequest dto, @MappingTarget Livro livro);

    // "Assim que você terminar de converter todos os campos normais (título, autor, etc.), 
    // execute este trecho de código antes de me devolver o objeto Livro."
    @AfterMapping
    default void vincularFilhosAoLivro(@MappingTarget Livro livro) {
        if (livro.getImagens() != null) {
            livro.getImagens().forEach(img -> img.setLivro(livro));
        }
    }
}