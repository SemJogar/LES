package app.mapper;

import app.dto.compra.request.CompraRequest;
import app.dto.compra.request.CompraLivroRequest;
import app.dto.compra.response.CompraResponse;
import app.model.compra.Compra;
import app.model.compra.CompraLivro;
import app.model.compra.CupomUso;
import app.model.cliente.Cliente;
import app.model.livro.Livro;
import app.model.compra.Cupom;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityManager;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class CompraMapper {

    @Autowired
    protected EntityManager entityManager;

    // 1. REQUISIÇÃO -> ENTIDADE (DTO -> Entidade)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "mapClienteProxy")
    @Mapping(target = "itens", source = "comprasLivros") 
    @Mapping(target = "cuponsUsados", source = "cuponsIds")
    public abstract Compra requestToCompra(CompraRequest request);

    // 2. ENTIDADE -> RESPOSTA (Entidade -> DTO)
    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "compraLivro", source = "itens") // Mapeia 'itens' da Entidade de volta para 'compraLivro' do DTO
    public abstract CompraResponse compraToResponse(Compra compra);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "mapClienteProxy")
    @Mapping(target = "itens", source = "comprasLivros")
    @Mapping(target = "cuponsUsados", source = "cuponsIds")
    public abstract void atualizarCompra(@MappingTarget Compra compra, CompraRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "compra", ignore = true)
    @Mapping(target = "livro", source = "livroId", qualifiedByName = "mapLivroProxy")
    public abstract CompraLivro requestToCompraLivro(CompraLivroRequest request);

    // 3. PROXIES JPA
    @Named("mapClienteProxy")
    public Cliente mapCliente(Integer id) {
        return id != null ? entityManager.getReference(Cliente.class, id) : null;
    }

    @Named("mapLivroProxy")
    public Livro mapLivro(Integer id) {
        return id != null ? entityManager.getReference(Livro.class, id) : null;
    }

    @Named("mapCupomProxy")
    public Cupom mapCupom(Integer id) {
        return id != null ? entityManager.getReference(Cupom.class, id) : null;
    }

    public CupomUso mapCupomIdToCupomUso(Integer cupomId) {
        if (cupomId == null) return null;
        CupomUso uso = new CupomUso();
        uso.setCupom(mapCupom(cupomId));
        return uso;
    }

    // 4. VÍNCULOS BIDIRECIONAIS (@AfterMapping)
    @AfterMapping
    protected void vincularFilhosACompra(@MappingTarget Compra compra) {
        // Amarra os itens (CompraLivro) à Compra pai
        if (compra.getItens() != null) {
            compra.getItens().forEach(item -> item.setCompra(compra));
        }

        // Amarra os cupons à Compra pai (caso a lista esteja na entidade)
        if (compra.getCuponsUsados() != null) {
            compra.getCuponsUsados().forEach(uso -> uso.setCompra(compra));
        }
    }
}