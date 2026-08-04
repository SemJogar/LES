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
public class ClienteService{
    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponse CadastrarCliente(ClienteRequest dto){

        Cliente clienteEntity = clienteMapper.requestToCliente(dto);

        Cliente clienteSalvo = clienteRepository.save(clienteEntity);

        return clienteMapper.clienteToResponse(clienteSalvo);
    }

    @Transactional
    public ClienteResponse EditarCliente(Integer id, ClienteRequest dto){

        Cliente clienteEntity = clienteRepository.findById(id)
            //Exception
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));

        //Nesse caso o Mapper recebe as informações do dto
        //ve em quais lugares mudou
        //e altera as informações dentro da entidade
        // nos lugares correspondentes
        clienteMapper.atualizarCliente(dto, clienteEntity);

        Cliente clienteSalvo = clienteRepository.save(clienteEntity);

        return clienteMapper.clienteToResponse(clienteSalvo);
    }

    @Transactional
    public void ExcluirCliente(Integer id, ClienteRequest dto){

        Cliente clienteEntity = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));

        clienteRepository.delete(clienteEntity);
    }
    
}