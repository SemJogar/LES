package app.repository.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.compra.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{

}