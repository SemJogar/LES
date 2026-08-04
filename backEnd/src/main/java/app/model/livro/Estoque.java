package app.model.livro;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "estoques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "est_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "est_liv_id")
    private Livro livro;

    @Column(name = "est_qt")
    private Integer qt;
    @Column(name = "est_custo", precision = 5, scale = 2)
    private BigDecimal custo;
    @Column(name = "est_fornecedor", length = 100)
    private String fornecedor;
    @Column(name = "est_dt_entrada")
    private LocalDateTime dtEntrada;
}