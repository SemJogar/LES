package app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.mapper.ClienteMapper;
import app.model.cliente.Cliente;
import app.repository.cliente.ClienteRepository;
import app.dto.cliente.request.ClienteRequest;
import app.dto.cliente.response.ClienteResponse;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponse cadastrarCliente(ClienteRequest dto) {
        // 1. O Mapper converte o DTO, cria os Proxies para FKs e vincula os relacionamentos bidirecionais
        Cliente clienteEntity = clienteMapper.requestToCliente(dto);

        // 2. O Spring Data JPA persiste a árvore inteira de entidades (Cliente, Endereço, Cartões, Perfil)
        Cliente clienteSalvo = clienteRepository.save(clienteEntity);

        // 3. Converte o resultado para DTO de Resposta
        return clienteMapper.clienteToResponse(clienteSalvo);
    }

    @Transactional(readOnly = true)
    public ClienteResponse visualizarCliente(Integer id) {
        Cliente clienteEntity = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));
        return clienteMapper.clienteToResponse(clienteEntity);
    }

    @Transactional
    public ClienteResponse editarCliente(Integer id, ClienteRequest dto) {
        Cliente clienteEntity = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));

        clienteMapper.atualizarCliente(clienteEntity, dto);
        Cliente clienteSalvo = clienteRepository.save(clienteEntity);

        return clienteMapper.clienteToResponse(clienteSalvo);
    }

    @Transactional
    public void deletarCliente(Integer id) {
        Cliente clienteEntity = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));

        clienteRepository.delete(clienteEntity);
    }
}