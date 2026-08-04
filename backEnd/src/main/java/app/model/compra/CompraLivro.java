package app.model.compra;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

// é necessario importar este em especifico, pois está em outro pacote
import app.model.livro.Livro;

@Entity
@Table(name = "compras_livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id")
    private Integer id;

    // Conexão com a Compra (Muitos itens de compra pertencem a uma Compra)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "col_com_id", nullable = false)
    private Compra compra;

    // Conexão com o Livro (Muitos itens de compra apontam para um Livro)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "col_liv_id", nullable = false)
    private Livro livro;

    @Column(name = "col_qt", nullable = false)
    private Short quantidade; // SMALLINT mapeia bem para Short ou Integer no Java

    @Column(name = "col_valor", nullable = false, precision = 5, scale = 2)
    private BigDecimal valor; // NUMERIC mapeia para BigDecimal
}