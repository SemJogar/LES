package app.repository.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.endereco.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}