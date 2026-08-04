package app.repository.livro;

import app.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//AVISO: ESSA INTERFACE SÓ SERIA UTIL,
//SE VOCE FOR FAZER QUERYS

//JPA -> traduz os comandos de java para o banco, ou seja,
//ele gera querys em sql
@Repository
public interface LivroRepository extends JpaRepository<Livro,Integer> {
	// O "JOIN FETCH" força o Hibernate a trazer a categoria na mesma query, 
    // ignorando o LAZY apenas para esta busca específica!
    //@Query("SELECT lc FROM LivroCategoria lc JOIN FETCH lc.categoria WHERE lc.livro = :livro")
    //List<LivroCategoria> findByLivro(@Param("livro") Livro livro);
    // List<Livro> findByTituloContainingIgnoreCase(String termo, Pageable pageable);
}