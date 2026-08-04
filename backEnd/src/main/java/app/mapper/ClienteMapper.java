package app.mapper;

import app.dto.cliente.request.ClienteRequest;
import app.dto.cliente.response.ClienteResponse;
import app.model.cliente.Cliente;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE // Ignora campos nulos no PUT
)
public interface ClienteMapper {

    // 1. DTO -> Entidade (Criação)
    @Mapping(target = "id", ignore = true)
    Cliente requestToCliente(ClienteRequest dto);

    // 2. Entidade -> DTO (Resposta)
    ClienteResponse clienteToResponse(Cliente cliente);

    // 3. Atualizar Entidade Existente (PUT)
    @Mapping(target = "id", ignore = true)
    // @Mapping(target = "imagens", ignore = true) // Imagens tratadas no AfterMapping para evitar quebra do Cascade
    void atualizarCliente(ClienteRequest dto, @MappingTarget Cliente cliente);

    // "Assim que você terminar de converter todos os campos normais (título, autor, etc.), 
    // execute este trecho de código antes de me devolver o objeto Livro."
    // @AfterMapping
    // default void vincularFilhosAoLivro(@MappingTarget Cliente livro) {
    //     if (livro.getImagens() != null) {
    //         livro.getImagens().forEach(img -> img.setLivro(livro));
    //     }
    // }
}