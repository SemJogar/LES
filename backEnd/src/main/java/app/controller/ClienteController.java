package app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.dto.cliente.request.ClienteRequest;
import app.dto.cliente.response.ClienteResponse;
import app.service.ClienteService;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> criar(@Valid @RequestBody ClienteRequest dto) {
        ClienteResponse novoCliente = clienteService.cadastrarCliente(dto);
        // Retorna o status HTTP 201 (Created) junto com os dados do livro salvos
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> visualizar(@PathVariable Integer id) {
        // O service busca e já devolve o LivroResponse (DTO)
        ClienteResponse cliente = clienteService.visualizarCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> editar(
            @PathVariable Integer id, 
            @Valid @RequestBody ClienteRequest dto
    ) {
        ClienteResponse clienteAtualizado = clienteService.editarCliente(id, dto);
        // Retorna o status HTTP 200 (OK) com o livro já modificado
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        
        // Retorna o status HTTP 204 (No Content)
        return ResponseEntity.noContent().build();
    }
}