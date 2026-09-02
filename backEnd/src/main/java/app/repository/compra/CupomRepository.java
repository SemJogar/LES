package app.repository.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.compra.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer>{

}