package app.mapper;

import app.dto.cliente.request.CartaoRequest;
import app.dto.cliente.request.ClienteRequest;
import app.dto.cliente.response.CartaoResponse;
import app.dto.cliente.response.ClienteResponse;
import app.dto.cliente.response.FuncaoResponse;
import app.model.cliente.Bandeira;
import app.model.cliente.Cartao;
import app.model.cliente.Cliente;
import app.model.cliente.Funcao;
import app.model.endereco.Cidade;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityManager;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class ClienteMapper {

    @Autowired
    protected EntityManager entityManager;

    // 1. DTO -> Entidade (Criação)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco.cidade", source = "endereco.cidadeId")
    @Mapping(target = "perfil.funcao", source = "perfil.funcaoId")
    public abstract Cliente requestToCliente(ClienteRequest request);

    // 2. Entidade -> DTO (Resposta)
    public abstract ClienteResponse clienteToResponse(Cliente cliente);

    // 3. Atualizar Entidade Existente (PUT) - O QUE FALTAVA!
    @Mapping(target = "id", ignore = true)
    // target se refere ao model no caso e source a request
    @Mapping(target = "endereco.cidade", source = "endereco.cidadeId")
    @Mapping(target = "perfil.funcao", source = "perfil.funcaoId")
    public abstract void atualizarCliente(@MappingTarget Cliente cliente, ClienteRequest request);

    // Mapeamentos de sub-objetos
    @Mapping(target = "bandeira", source = "bandeiraId")
    public abstract Cartao requestToCartao(CartaoRequest request);
    public abstract CartaoResponse cartaoToCartaoResponse(Cartao cartao);
    
    public abstract FuncaoResponse funcaoToFuncaoResponse(Funcao funcao);

    // Conversões de ID para Proxy JPA (Zero SELECTs)
    // ele cria um entidade falsa só para o java entender que ela já existe e associar ao id 1
    public Cidade mapCidade(Integer id) {
        return id != null ? entityManager.getReference(Cidade.class, id) : null;
    }

    public Funcao mapFuncao(Integer id) {
        return id != null ? entityManager.getReference(Funcao.class, id) : null;
    }

    public Bandeira mapBandeira(Integer id) {
        return id != null ? entityManager.getReference(Bandeira.class, id) : null;
    }

    // Ajuste dos vínculos bidirecionais
    @AfterMapping
    protected void vincularFilhosAoCliente(@MappingTarget Cliente cliente) {
        if (cliente.getTelefones() != null) {
            cliente.getTelefones().forEach(tel -> tel.setCliente(cliente));
        }
        if (cliente.getCartoes() != null) {
            cliente.getCartoes().forEach(cartao -> cartao.setCliente(cliente));
        }
        if (cliente.getPerfil() != null) {
            cliente.getPerfil().setCliente(cliente);
        }
    }
}