package app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.dto.compra.request.CompraRequest;
import app.dto.compra.response.CompraResponse;
import app.service.CompraService;

import java.util.List;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraResponse> criar(@Valid @RequestBody CompraRequest dto) {
        CompraResponse novaCompra = compraService.criarCompra(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCompra);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> visualizar(@PathVariable Integer id) {
        CompraResponse compra = compraService.visualizarCompra(id);
        return ResponseEntity.ok(compra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraResponse> editar(
            @PathVariable Integer id, 
            @Valid @RequestBody CompraRequest dto
    ) {
        CompraResponse compraAtualizada = compraService.editarCompra(dto, id);
        return ResponseEntity.ok(compraAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        compraService.deletarCompra(id);
        return ResponseEntity.noContent().build();
    }
}