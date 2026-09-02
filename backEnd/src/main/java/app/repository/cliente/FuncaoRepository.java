package app.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.cliente.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Integer>{

}