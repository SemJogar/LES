package app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.model.livro.Livro;
import app.dto.livro.request.LivroRequest;
import app.dto.livro.response.LivroResponse;
import app.mapper.LivroMapper;
import app.repository.livro.LivroRepository;
import app.model.livro.Categoria;
import app.repository.livro.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final CategoriaRepository categoriaRepository;
    private final LivroMapper livroMapper;

    // public List<LivroSugestaoDTO> buscarSugestoes(String termo) {
    //     // Limita a busca em no máximo 5 itens para ser super rápido
    //     var limite = PageRequest.of(0, 5);

    //     return livroRepository.findByTituloContainingIgnoreCase(termo, limite)
    //             .stream()
    //             .map(LivroSugestaoDTO::doModel)
    //             .toList();
    // }

    @Transactional
    public LivroResponse criarLivro(LivroRequest dto) { // Ajustado para os DTOs corretos
        // 1. Converte RequestDTO para a Entidade Livro
        Livro livroEntity = livroMapper.requestToLivro(dto);
        
        if (dto.categoriaId() != null && !dto.categoriaId().isEmpty()) {
            // 1. Busca todas as categorias de uma vez só
            List<Categoria> categoriasReais = categoriaRepository.findAllById(dto.categoriaId());
            
            // 2. Validação opcional: se o banco achou menos categorias do que o enviado, algum ID está errado
            if (categoriasReais.size() != dto.categoriaId().size()) {
                throw new RuntimeException("Uma ou mais categorias informadas não foram encontradas.");
            }
            
            livroEntity.setCategorias(categoriasReais);
        }
        
        livroEntity.setStatus(true);

        // 2. Salva no banco
        Livro livroSalvo = livroRepository.save(livroEntity);
        
        // 3. Converte a Entidade salva para ResponseDTO
        return livroMapper.livroToResponse(livroSalvo);
    }

    public LivroResponse visualizarLivro(Integer id) {
        Livro livroEntity = livroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));
        return livroMapper.livroToResponse(livroEntity);
    }
    
    @Transactional
    public LivroResponse editarLivro(Integer id, LivroRequest dto) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));

        // 1. Atualiza campos simples, dimensao, precificacao e imagens via MapStruct
        livroMapper.atualizarLivro(dto, livroExistente);

        // 2. Atualiza as categorias manualmente (pois foram ignoradas no Mapper)
        if (dto.categoriaId() != null && !dto.categoriaId().isEmpty()) {
            List<Categoria> categoriasReais = categoriaRepository.findAllById(dto.categoriaId());
            
            if (categoriasReais.size() != dto.categoriaId().size()) {
                throw new RuntimeException("Uma ou mais categorias informadas não foram encontradas.");
            }
            
            livroExistente.setCategorias(categoriasReais);
        }

        Livro livroAtualizado = livroRepository.save(livroExistente);
        return livroMapper.livroToResponse(livroAtualizado);
    }

    @Transactional
    public void deletarLivro(Integer id) {
        // 1. Busca o livro existente ou lança uma exceção se não encontrar
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));

        // 2. Deleta o livro do banco de dados
        livroRepository.delete(livroExistente);
    }
}
