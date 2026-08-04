// package app.service;

// import app.dto.livro.request.LivroRequest;
// import app.dto.livro.response.LivroResponse;
// import app.mapper.LivroMapper;
// import app.model.livro.Categoria;
// import app.model.livro.Livro;
// import app.repository.livro.CategoriaRepository;
// import app.repository.livro.LivroRepository;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// class LivroServiceTest {

//     @Mock
//     private LivroRepository livroRepository;

//     @Mock
//     private CategoriaRepository categoriaRepository;

//     @Mock
//     private LivroMapper livroMapper;

//     @InjectMocks
//     private LivroService livroService;

//     private LivroRequest requestDTO;
//     private LivroResponse responseDTO;
//     private Livro livro;
//     private Categoria categoria;

//     @BeforeEach
//     void setUp() {
//         // Criando instâncias completas do Record (com todos os 13 parâmetros)
//         // para não ter erro de compilação!
//         requestDTO = new LivroRequest(
//                 1, "J.R.R. Tolkien", List.of(1), 1954, "O Senhor dos Anéis",
//                 "HarperCollins", "1ª", "978-0008376123", 1200, "Sinopse do livro",
//                 null, null, "1234567890123"
//         );

//         categoria = new Categoria();
//         categoria.setId(1);

//         livro = new Livro();
//         livro.setId(1);
//         livro.setAutor("J.R.R. Tolkien");
//         livro.setCategorias(List.of(categoria));

//         responseDTO = new LivroResponse(
//                 1, "J.R.R. Tolkien", List.of(categoria), 1954, "O Senhor dos Anéis",
//                 "HarperCollins", "1ª", "978-0008376123", 1200, "Sinopse do livro",
//                 null, null, "1234567890123"
//         );
//     }

//     @Test
//     @DisplayName("Deve buscar sugestões de livros limitadas a 5 itens")
//     void buscarSugestoesComSucesso() {
//         PageRequest limite = PageRequest.of(0, 5);
//         when(livroRepository.findByTituloContainingIgnoreCase(eq("Harry"), eq(limite)))
//                 .thenReturn(List.of(livro));

//         // Executa e valida se retornou os DTOs de sugestão
//         List<LivroSugestao> resultado = livroService.buscarSugestoes("Harry");
        
//         assertNotNull(resultado);
//         assertEquals(1, resultado.size());
//     }
    
//     @Test
//     @DisplayName("Deve criar um livro com sucesso")
//     void criarLivroComSucesso() {
//         // Mock das dependências
//         when(livroMapper.requestToLivro(any(LivroRequest.class))).thenReturn(livro);
//         when(categoriaRepository.findAllById(any())).thenReturn(List.of(categoria));
//         when(livroRepository.save(any(Livro.class))).thenReturn(livro);
//         when(livroMapper.livroToResponse(any(Livro.class))).thenReturn(responseDTO);

//         // Execução
//         LivroResponse resultado = livroService.criarLivro(requestDTO);

//         // Verificações
//         assertNotNull(resultado);
//         assertEquals("J.R.R. Tolkien", resultado.autor());
//         verify(categoriaRepository, times(1)).findAllById(requestDTO.categoriaId());
//         verify(livroRepository, times(1)).save(livro);
//     }

//     @Test
//     @DisplayName("Deve lançar exceção ao tentar criar livro com categoria inexistente")
//     void criarLivroComCategoriaInexistenteLancaExcecao() {
//         when(livroMapper.requestToLivro(any(LivroRequest.class))).thenReturn(livro);
//         // Retorna lista vazia indicando que a categoria não foi achada no banco
//         when(categoriaRepository.findAllById(any())).thenReturn(List.of());

//         RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//             livroService.criarLivro(requestDTO);
//         });

//         assertEquals("Uma ou mais categorias informadas não foram encontradas.", exception.getMessage());
//         verify(livroRepository, never()).save(any());
//     }

//     @Test
//     @DisplayName("Deve editar um livro com sucesso")
//     void editarLivroComSucesso() {
//         when(livroRepository.findById(1)).thenReturn(Optional.of(livro));
//         doNothing().when(livroMapper).atualizarLivro(requestDTO, livro);
//         when(livroRepository.save(livro)).thenReturn(livro);
//         when(livroMapper.livroToResponse(livro)).thenReturn(responseDTO);

//         LivroResponse resultado = livroService.editarLivro(1, requestDTO);

//         assertNotNull(resultado);
//         verify(livroRepository, times(1)).findById(1);
//         verify(livroRepository, times(1)).save(livro);
//     }

//     @Test
//     @DisplayName("Deve deletar um livro com sucesso")
//     void deletarLivroComSucesso() {
//         when(livroRepository.findById(1)).thenReturn(Optional.of(livro));
//         doNothing().when(livroRepository).delete(livro);

//         assertDoesNotThrow(() -> livroService.deletarLivro(1));

//         verify(livroRepository, times(1)).findById(1);
//         verify(livroRepository, times(1)).delete(livro);
//     }

//     @Test
//     @DisplayName("Deve lançar exceção ao tentar deletar livro inexistente")
//     void deletarLivroInexistenteLancaExcecao() {
//         when(livroRepository.findById(99)).thenReturn(Optional.empty());

//         RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//             livroService.deletarLivro(99);
//         });

//         assertEquals("Livro não encontrado com o ID: 99", exception.getMessage());
//         verify(livroRepository, never()).delete(any());
//     }
// }