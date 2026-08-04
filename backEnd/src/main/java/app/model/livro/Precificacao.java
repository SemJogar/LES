package app.model.livro;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "precificacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Precificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pre_id")
    private Integer id;

    @Column(name = "pre_grupo", length = 20)
    private String grupo;

    @Column(name = "pre_lucro", precision = 5, scale = 2)
    private BigDecimal lucro;

    // @OneToOne
    // @JoinColumn(name = "pre_liv_id")
    // private Livro livro;
}