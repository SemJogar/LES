package app.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.cliente.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer>{

}