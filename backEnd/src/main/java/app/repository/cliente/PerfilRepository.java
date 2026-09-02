package app.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.cliente.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}