package app.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.cliente.Bandeira;

@Repository
public interface BandeiraRepository extends JpaRepository<Bandeira, Integer>{

}