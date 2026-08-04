package app.repository.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.endereco.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}