package app.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.cliente.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}