package app.model.livro;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "dimensoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Dimensao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dim_id")
    private Integer id;

    @Column(name = "dim_altura", precision = 5, scale = 2)
    private BigDecimal altura;
    @Column(name = "dim_largura", precision = 5, scale = 2)
    private BigDecimal largura;
    @Column(name = "dim_peso", precision = 5, scale = 2)
    private BigDecimal peso;
    @Column(name = "dim_profundidade", precision = 5, scale = 2)
    private BigDecimal profundidade;

    @OneToOne(mappedBy = "dimensao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Livro livro;
}
