package app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.dto.livro.request.LivroRequest;
import app.dto.livro.response.LivroResponse;
import app.service.LivroService;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroResponse> criar(@Valid @RequestBody LivroRequest dto) {
        LivroResponse novoLivro = livroService.criarLivro(dto);
        // Retorna o status HTTP 201 (Created) junto com os dados do livro salvos
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> visualizar(@PathVariable Integer id) {
        // O service busca e já devolve o LivroResponse (DTO)
        LivroResponse livro = livroService.visualizarLivro(id);
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> editar(
            @PathVariable Integer id, 
            @Valid @RequestBody LivroRequest dto
    ) {
        LivroResponse livroAtualizado = livroService.editarLivro(id, dto);
        // Retorna o status HTTP 200 (OK) com o livro já modificado
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        livroService.deletarLivro(id);
        
        // Retorna o status HTTP 204 (No Content)
        return ResponseEntity.noContent().build();
    }
}