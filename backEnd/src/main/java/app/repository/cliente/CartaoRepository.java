package app.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.cliente.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer>{

}