package app.repository.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.compra.CupomUso;

@Repository
public interface CupomUsosRepository extends JpaRepository<CupomUso, Integer>{

}