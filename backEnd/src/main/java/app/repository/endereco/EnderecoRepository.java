package app.repository.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.endereco.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}