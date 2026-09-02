package app.repository.cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.cliente.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	// O Spring gera automaticamente a consulta: SELECT * FROM cliente WHERE email = ?
	// No caso é email, pois ele pega o atributo que foi mapeado dentro
	// do model.cliente que é email = cli_email
    // Optional<Cliente> findByEmail(String email);
}