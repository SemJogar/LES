package app.repository.livro;

import app.model.livro.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//AVISO: ESSA INTERFACE SÓ SERIA UTIL,
//SE VOCE FOR FAZER QUERYS

//JPA -> traduz os comandos de java para o banco, ou seja,
//ele gera querys em sql
@Repository
public interface EstoqueRepository extends JpaRepository<Estoque,Integer> {

}