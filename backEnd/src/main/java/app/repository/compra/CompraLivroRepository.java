package app.repository.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.compra.CompraLivro;

@Repository
public interface CompraLivroRepository extends JpaRepository<CompraLivro, Integer>{

}